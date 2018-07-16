/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TCustomerSubscribe bean) {
		try {
			bean.setUpdateTime(DateUtil.getSysCurrentDate());
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			if(bean.getId()!=null) {
				bean.setUpdateUser(user.getUsername());
				customerSubscribeMapper.updateByPrimaryKeySelective(bean);
				if(bean.getStatus()==1) {
					subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_RECEPTION,user.getUsername()));
				}else if(bean.getStatus()==0) {
					subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_EDIT,user.getUsername()));
				}else if(bean.getStatus()==99) {
					subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_CLOSE,user.getUsername()));
				}
			}else {
				bean.setCreateTime(DateUtil.getSysCurrentDate());
				bean.setCreateUser(user.getUsername());
				bean.setStatus(0);
				customerSubscribeMapper.insertSelective(bean);
				subscribeLogService.insertSelective(new TCustomerSubscribeLog(bean.getId(),InitProperties.SUBSCRIBE_OPTION_TYPE_ADD,user.getUsername()));
				String mess = "后台用户："+SystemUserInfo.getSystemUser().getUser().getNickname()+",在"+DateUtil.getAllCurrentDate()+"成功预约了一个客户！\n 客户姓名："+bean.getCustomerName()+"\n 联系方式："+bean.getCustomerPhone()+"\n预约到诊时间："+DateUtil.dateToString(bean.getSubscribeDate());
				activeManager.sendBack(ActiveMQUtil.getTopicDestination(bean.getCompanyId()+InitProperties.BACK_SUBSCRIBE_MSG),mess);
				Map<String,Object> map = new HashMap<>();
				map.put("companyid", bean.getCompanyId());
				map.put("roles", RoleSign.Q_ALL);
				List<TSysUser> userlist = userMapper.queryQUserEmailAndPhone(map);
				Object[] emails = userlist.stream().map(f->f.getEmail()).distinct().toArray();
				emailService.sendSimpleEmail(sourceEmail, emails, "痘卫士-预约提醒",mess);
			}
			TVipCustomerExample vcexample = new TVipCustomerExample();
			vcexample.createCriteria().andCustomerPhoneEqualTo(bean.getCustomerPhone());
			List<TVipCustomer> list = vipCustomerSergice.selectByExample(vcexample);
			if(list.size()>0) {
				TVipCustomer customer = list.get(0);
				if(StringUtils.isNotBlank(bean.getCustomerName()))customer.setCustomerName(bean.getCustomerName());
				if(StringUtils.isNotBlank(bean.getCustomerPhone()))customer.setCustomerPhone(bean.getCustomerPhone());
				if(StringUtils.isNotBlank(bean.getEmail()))customer.setEmail(bean.getEmail());
				if(bean.getSex()!=null)customer.setSex(bean.getSex());
				vipCustomerSergice.updateByPrimaryKeySelective(customer);
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
					vip.setCreateTime(DateUtil.getSysCurrentDate());
					vip.setCreateUser(SystemUserInfo.getSystemUser().getUser().getUsername());
					vipCustomerSergice.insertSelective(vip);
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
						}
					}
				}
			}
			return true;
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return false;
	}

}
