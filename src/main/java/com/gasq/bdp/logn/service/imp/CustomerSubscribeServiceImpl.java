/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.component.ActiveManager;
import com.gasq.bdp.logn.mapper.TCustomerCommentMapper;
import com.gasq.bdp.logn.mapper.TCustomerSubscribeLogMapper;
import com.gasq.bdp.logn.mapper.TCustomerSubscribeMapper;
import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerComment;
import com.gasq.bdp.logn.model.TCustomerCommentExample;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TCustomerSubscribeExample;
import com.gasq.bdp.logn.model.TCustomerSubscribeExample.Criteria;
import com.gasq.bdp.logn.model.TCustomerSubscribeLog;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.service.CustomerSubscribeService;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.utils.ActiveMQUtil;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class CustomerSubscribeServiceImpl implements CustomerSubscribeService {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired TCustomerSubscribeMapper customerSubscribeMapper;
	@Autowired TCustomerSubscribeLogMapper subscribeLogService;
	@Autowired ActiveManager activeManager;
	@Autowired TVipCustomerMapper vipCustomerSergice;
	@Autowired TCustomerCommentMapper customerCommentService;
	@Autowired EmailManager emailService;
	@Autowired TSysUserMapper userMapper;
	
	@Value("${spring.mail.username}")
	private String sourceEmail;

	@Override
	public long countByExample(TCustomerSubscribeExample example) {
		return customerSubscribeMapper.countByExample(example);
	}
	
	public long countByExample(TCustomerSubscribe bean) {
		TCustomerSubscribeExample example = new TCustomerSubscribeExample();
		Criteria c = example.createCriteria();
		if(bean.getCustomerName()!=null && !"".equals(bean.getCustomerName())) {
			c.andCustomerNameLike("%"+bean.getCustomerName()+"%");
		}
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(StringUtils.isNotBlank(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		return customerSubscribeMapper.countByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id) {
		try {
			TCustomerSubscribe subscribe = customerSubscribeMapper.selectByPrimaryKey(id);
			subscribe.setStatus(99);
			this.saveOrUpdate(subscribe);
			subscribeLogService.insertSelective(new TCustomerSubscribeLog(subscribe.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_CLOSE,SystemUserInfo.getSystemUser().getUser().getUsername()));
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】成功关闭客户预约信息并添加预约关闭日志！");
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】关闭客户预约信息失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}

	@Override
	public List<TCustomerSubscribe> selectByExample(TCustomerSubscribe bean) {
		TCustomerSubscribeExample example = new TCustomerSubscribeExample();
		Criteria c = example.createCriteria();
		if(bean.getCustomerName()!=null && !"".equals(bean.getCustomerName())) {
			c.andCustomerNameLike("%"+bean.getCustomerName()+"%");
		}
		if(bean.getCompanyId()!=null) {
			c.andCompanyIdEqualTo(bean.getCompanyId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(StringUtils.isNotBlank(bean.getRemark())) {
			c.andRemarkLike("%"+bean.getRemark()+"%");
		}
		example.setOrderByClause(" id asc ");
		return customerSubscribeMapper.selectByExample(example);
	}

	@Override
	public TCustomerSubscribe selectByPrimaryKey(Integer id) {
		return customerSubscribeMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TCustomerSubscribe bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
		params.put("companyid", SystemUserInfo.getSystemUser().getUser().getCompanyid());
		if(bean.getCustomerName()!=null) {
			params.put("customername", bean.getCustomerName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		if(bean.getRemark()!=null) {
			params.put("remark", bean.getRemark());
		}
		if(bean.getCompanyId()!=null) {
			params.put("companyid", bean.getCompanyId());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		list = customerSubscribeMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		Integer count = customerSubscribeMapper.countByBean(params);
		result.put("rows",list);
		result.put("total",count);
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询预约信息列表完成！查询条数："+count+",查询参数："+CommonUtils.bean2Json(params));
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TCustomerSubscribe bean) {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			if(bean.getId()!=null) {
				if(WorkFlowUtil.hasAnyRoles(RoleSign.Q_ADMIN,RoleSign.Q_OPTION,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.Q_COUNELOR,RoleSign.Q_RECEPTIONIST)){
					bean.setUpdateTime(DateUtil.getSysCurrentDate());
					bean.setUpdateUser(user.getNickname());
				}
				customerSubscribeMapper.updateByPrimaryKeySelective(bean);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新预约信息成功！");
				if(bean.getStatus()==1) {
					subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_RECEPTION,user.getUsername()));
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加前台接诊预约日志信息成功！");
				}else if(bean.getStatus()==0) {
					subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_EDIT,user.getUsername()));
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加修改预约日志信息成功！");
				}else if(bean.getStatus()==99) {
					subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_CLOSE,user.getUsername()));
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加关闭预约日志信息成功！");
				}
			}else {
				bean.setCreateUser(user.getUsername());
				bean.setStatus(0);
				customerSubscribeMapper.insertSelective(bean);
				subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_ADD,user.getUsername()));
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加预约日志成功！");
				String mess = "◆后台用户："+SystemUserInfo.getSystemUser().getUser().getNickname()+",在"+DateUtil.getAllCurrentDate()+"成功预约了一个客户！\n◆客户姓名："+bean.getCustomerName()+"\n◆联系方式："+bean.getCustomerPhone()+"\n◆预约到诊时间："+DateUtil.dateToString(bean.getSubscribeDate());
				activeManager.sendBack(ActiveMQUtil.getTopicDestination(bean.getCompanyId()+InitProperties.BACK_SUBSCRIBE_MSG),mess);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】系统通知发送成功！发送内容为："+mess);
//				Map<String,Object> map = new HashMap<>();
//				map.put("companyid", bean.getCompanyId());
//				List<String> sList = new ArrayList<String>();
//				sList.add(RoleSign.SADMIN);
//				map.put("roles", RoleSign.Q_ALL);
//				List<TSysUser> userlist = userMapper.queryQUserEmailAndPhone(map);
//				Object[] emails = userlist.stream().map(f->f.getEmail()).distinct().toArray();
//				emailService.sendHtmlMails(emails, "痘卫士-客户预约提醒",mess);
//				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】邮件通知发送成功！发送内容为："+mess);
//				SyncAction sa = new SyncAction(emailService,userMapper,bean.getCompanyId());
//				SyncAction.getExec().submit(sa);
			}
			TVipCustomerExample vcexample = new TVipCustomerExample();
			vcexample.createCriteria().andCustomerPhoneEqualTo(bean.getCustomerPhone());
			List<TVipCustomer> list = vipCustomerSergice.selectByExample(vcexample);
			if(list.size()>0) {
				TVipCustomer customer = list.get(0);
				if(StringUtils.isNotBlank(bean.getCustomerName()))customer.setCustomerName(bean.getCustomerName());
				if(StringUtils.isNotBlank(bean.getCustomerPhone()))customer.setCustomerPhone(bean.getCustomerPhone());
				if(StringUtils.isNotBlank(bean.getEmail()))customer.setEmail(bean.getEmail());
				if(bean.getProfession()!=null)customer.setProfession(bean.getProfession());
				if(bean.getSex()!=null)customer.setSex(bean.getSex());
				vipCustomerSergice.updateByPrimaryKeySelective(customer);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新会员用户信息成功！");
				if(StringUtils.isNotBlank(bean.getRemark())) {
					TCustomerCommentExample tccexample = new TCustomerCommentExample();
					tccexample.createCriteria().andRemarkEqualTo(bean.getRemark()).andVipIdEqualTo(customer.getId());
					long l = customerCommentService.countByExample(tccexample);
					if(l<=0) {
						TCustomerComment cc = new TCustomerComment();
						cc.setRemark(bean.getRemark());
						cc.setVipId(customer.getId());
						cc.setCreateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
						cc.setCreateTime(DateUtil.getSysCurrentDate());
						customerCommentService.insertSelective(cc);
						logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加顾客留言信息成功！");
					}
				}
			}else {
				if(StringUtils.isNotBlank(bean.getCustomerPhone())) {
					TVipCustomer vip = new TVipCustomer();
					vip.setCompanyId(bean.getCompanyId());
					vip.setCustomerName(bean.getCustomerName());
					vip.setCustomerPhone(bean.getCustomerPhone());
					vip.setSex(bean.getSex());
					vip.setStatus(1);
					vip.setProfession(bean.getProfession());
					vip.setCreateTime(DateUtil.getSysCurrentDate());
					vip.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
					vipCustomerSergice.insertSelective(vip);
					logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】新增顾客信息成功！");
					if(StringUtils.isNotBlank(bean.getRemark())) {
						TCustomerCommentExample tccexample = new TCustomerCommentExample();
						tccexample.createCriteria().andRemarkEqualTo(bean.getRemark()).andVipIdEqualTo(vip.getId());
						long l = customerCommentService.countByExample(tccexample);
						if(l<=0) {
							TCustomerComment cc = new TCustomerComment();
							cc.setRemark(bean.getRemark());
							cc.setVipId(vip.getId());
							cc.setCreateUser(SystemUserInfo.getSystemUser().getUser().getNickname());
							cc.setCreateTime(DateUtil.getSysCurrentDate());
							customerCommentService.insertSelective(cc);
							logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加顾客留言信息成功！");
						}
					}
				}
			}
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】新增或更新客户预约操作完成！");
			return true;
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】新增或更新客户预约操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}

	@Override
	public String querySubscribeReceptionInfo() {
		String mess = "";
		List<Map<String, Object>> maps = customerSubscribeMapper.querySubscribeReceptionInfo(SystemUserInfo.getSystemUser().getUser().getUsername());
		if(maps!=null) {
			if(maps.size()>0) {
				String nickname = maps.get(0).get("nickname").toString();
				mess+="★用户【"+nickname+"】";
				for (Map<String, Object> map : maps) {
					String customer_name = map.get("customer_name").toString();
					String datetime = map.get("datetime").toString();
					if(StringUtils.isNotBlank(customer_name)) {
						mess += ":在"+datetime+"有未到诊顾客【"+customer_name+"】，共计"+customer_name.split(",").length+"人！:";
					}
				}
				if(mess.length()>0) {
					String jiezhennumbs = maps.get(0).get("jiezhennumbs").toString();
					mess +="★昨日到店接诊："+jiezhennumbs+"人";
				}
			}
//			activeManager.sendBack(ActiveMQUtil.getTopicDestination(SystemUserInfo.getSystemUser().getUser().getCompanyid()+InitProperties.BACK_SUBSCRIBE_DAY_ALL_MSG),mess);
//			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】预约未到诊情况系统通知发送成功！\n发送内容为："+mess);
		}
		return mess;
	}

	@Override
	public String querySubscribeInfo() {
		String mess = "";
		List<Map<String, Object>> maps = customerSubscribeMapper.querySubscribeInfo(SystemUserInfo.getSystemUser().getUser().getCompanyid());
		if(maps!=null) {
			for (Map<String, Object> map : maps) {
				String customer_name = map.get("customer_name").toString();
				String datetime = map.get("datetime").toString();
				String nickname = map.get("nickname").toString();
				if(StringUtils.isNotBlank(customer_name)) {
					mess += "★用户【"+nickname+"】在"+datetime+"成功预约了新客户【"+customer_name+"】，共预约"+customer_name.split(",").length+"人！:";
				}
			}
			if(mess.length()>0)	{
				String daozhennumbs = maps.get(0).get("daozhennumbs").toString();
				mess +="★今天可到店接诊人数："+daozhennumbs+"人";
			}
//			activeManager.sendBack(ActiveMQUtil.getTopicDestination(SystemUserInfo.getSystemUser().getUser().getCompanyid()+InitProperties.BACK_SUBSCRIBE_DAY_ALL_MSG),mess);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】前台预约接诊情况系统通知发送成功！\n发送内容为："+mess);
		}
		return mess;
	}

}
