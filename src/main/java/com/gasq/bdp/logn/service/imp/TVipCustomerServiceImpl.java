/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.mapper.TConsumptonProjectMapper;
import com.gasq.bdp.logn.mapper.TCustomerConsumptonAmountMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.mapper.TMessageMapper;
import com.gasq.bdp.logn.mapper.TProjectMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerLogMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TConsumptonProject;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmountExample;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerExample;
import com.gasq.bdp.logn.model.TMessage;
import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.model.TProjectExample;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.model.TVipCustomerExample.Criteria;
import com.gasq.bdp.logn.model.TVipCustomerLog;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TVipCustomerService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TVipCustomerServiceImpl implements TVipCustomerService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TVipCustomerMapper mapper;
	@Autowired TVipCustomerLogMapper customerLogMapper;
	@Autowired TLtnCustomerMapper customerMapper;
	@Autowired TCustomerConsumptonAmountMapper consumptonAmountMapper;
	@Autowired TMessageMapper messagemapper;
	@Autowired EmailManager emailManager;
	@Autowired TProjectMapper projectMapper;
	@Autowired TConsumptonProjectMapper consumptonProjectMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id) {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TVipCustomer vipCustomer = mapper.selectByPrimaryKey(id);
			vipCustomer.setStatus(99);
			mapper.updateByPrimaryKeySelective(vipCustomer);
			customerLogMapper.insertSelective(new TVipCustomerLog(id,InitProperties.CUSTOMER_CONSUMPTON_OPTION_CLOSE,null,user.getCompanyid(),user.getUsername()));
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	@Override
	public int add(TVipCustomer record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<TVipCustomer> selectByExample(TVipCustomerExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public TVipCustomer selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TVipCustomer bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		if(bean.getCompanyId()!=null) {
			bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				bean.setCompanyId(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getStatus()==null) {
			if(WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.Q_ADMIN)) {
				String statuss = String.join(",","1","99");
				bean.setStatuss(statuss.split(","));
			}else {
				String statuss = String.join(",","1");
				bean.setStatuss(statuss.split(","));
			}
		}else {
			String statuss = String.join(",",bean.getStatus().toString());
			bean.setStatuss(statuss.split(","));
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = mapper.queryPagingList(bean);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询会员用户信息列表完成！查询条数："+pageinfo.getTotal()+",查询参数："+CommonUtils.bean2Json(bean));
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TVipCustomer bean) {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			bean.setCompanyId(user.getCompanyid());
			bean.setUpdateUser(user.getUsername());
			if(bean.getGiveAmount()==null) bean.setGiveAmount(new BigDecimal(0));
			if(bean.getActualAmount()==null) bean.setActualAmount(new BigDecimal(0));
			if(bean.getId()!=null) {
				bean.setUpdateTime(DateUtil.getSysCurrentDate());
				mapper.updateByPrimaryKeySelective(bean);
				customerLogMapper.insertSelective(new TVipCustomerLog(bean.getId(),InitProperties.CUSTOMER_CONSUMPTON_OPTION_EDIT,bean.getActualAmount().add(bean.getGiveAmount()),user.getCompanyid(),user.getUsername()));
			}else {
				bean.setCreateUser(user.getUsername());
				bean.setUpdateTime(DateUtil.getSysCurrentDate());
				bean.setCreateTime(DateUtil.getSysCurrentDate());
				mapper.insertSelective(bean);
				customerLogMapper.insertSelective(new TVipCustomerLog(bean.getId(),InitProperties.CUSTOMER_CONSUMPTON_OPTION_ADD,bean.getActualAmount().add(bean.getGiveAmount()),user.getCompanyid(),user.getUsername()));
			}
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新或插入会员用户信息成功！");
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】插入退操或会员用户失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}

	@Override
	public Map<String, Object> sendMessage(TVipCustomer bean) throws WorkFlowJobException {
		Map<String, Object> result= new  HashMap<String, Object>();
		try {
			TVipCustomerExample example = new TVipCustomerExample();
			Criteria c = example.createCriteria();
			if(StringUtils.isNotBlank(bean.getCustomerName())) {
				c.andCustomerNameLike("%"+bean.getCustomerName()+"%");
			}
			if(StringUtils.isNotBlank(bean.getCustomerPhone())) {
				c.andCustomerPhoneLike("%"+bean.getCustomerPhone()+"%");
			}
			if(bean.getStatus()!=null) {
				c.andStatusEqualTo(bean.getStatus());
			}else {
				if(WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.Q_ADMIN)) {
					ArrayList<Integer> arrayList = new ArrayList<Integer>();
					arrayList.add(1);
					arrayList.add(99);
					c.andStatusIn(arrayList);
				}else {
					c.andStatusEqualTo(1);
				}
			}
			if(bean.getCreateTime()!=null) {
				c.andCreateTimeGreaterThanOrEqualTo(bean.getCreateTime());
			}
			if(bean.getEndtime()!=null) {
				c.andCreateTimeLessThan(bean.getEndtime());
			}
			if(bean.getCompanyId()!=null) {
				c.andCompanyIdEqualTo(bean.getCompanyId());
			}else {
				if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
					c.andCompanyIdEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
				}
			}
			c.andEmailIsNotNull();
			c.andEmailNotEqualTo("");
			List<TVipCustomer> list = mapper.selectByExample(example);
			Object[] emails = null;
			if(list.size()>0) {
				emails = list.stream().map(f->f.getEmail()).distinct().toArray();
				TMessage message = messagemapper.selectByPrimaryKey(bean.getMessid());
				if(bean.getMesstype()==1) {//邮件
					emailManager.sendSimpleEmail(emails, message.getTitle(), message.getMessage());
				}else if(bean.getType()==2) {//短信
					
				}
			}
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】发送消息成功！共发送消息【"+emails.length+"】条！");
			result.put("status", true);
			result.put("mess", "发送消息成功！共发送消息【"+emails.length+"】条！");
		} catch (Exception e) {
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】发送消息操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
			throw new WorkFlowJobException("发送消息失败！"+e.getMessage(),e);
		}
		return result;
	}

	@Override
	public boolean createConsumptonOrder(TVipCustomer bean) {
		TSysUser user = SystemUserInfo.getSystemUser().getUser();
		TLtnCustomer customer = new TLtnCustomer();
		customer.setCustomername(bean.getCustomerName());
		customer.setPhonenumb(bean.getCustomerPhone());
		customer.setCardId(bean.getEmail());
		customer.setChuFuZhen(1);
		customer.setStatus(0);
		customer.setCompanyId(user.getCompanyid());
		customer.setCreateuser(user.getUsername());
		customer.setProfession(bean.getProfession());
		customer.setSex(bean.getSex());
		customerMapper.insertSelective(customer);
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】从会员信息快速生成客户消费订单信息成功！生成内容："+CommonUtils.bean2Json(customer));
		return true;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean transferTreatment(TVipCustomer bean)throws Exception {
		try {
			if(bean.getCompanyId()==bean.getCompanyId1()) return false;
			TVipCustomer vipcustomer = mapper.selectByPrimaryKey(bean.getId());
			Integer companyId1 = bean.getCompanyId1();
			//------插入新用户信息
			vipcustomer.setCompanyId(companyId1);
			vipcustomer.setId(null);
			vipcustomer.setCreateUser(null);
			vipcustomer.setCreateTime(DateUtil.getSysCurrentDate());
			vipcustomer.setUpdateTime(DateUtil.getSysCurrentDate());
			vipcustomer.setUpdateUser(null);
			mapper.insertSelective(vipcustomer);
			//----------获取用户订单信息
			TLtnCustomerExample example = new TLtnCustomerExample();
			example.createCriteria().andCompanyIdEqualTo(bean.getCompanyId()).andPhonenumbEqualTo(vipcustomer.getCustomerPhone());
			List<TLtnCustomer> customerlist = customerMapper.selectByExample(example);
			for (TLtnCustomer customer : customerlist) {
				Integer orderid = customer.getId();
				//------更新用户订单信息为转入门店信息
				customer.setCompanyId(companyId1);
				customer.setId(null);
				customer.setCreateuser(null);
				customer.setCounsoler(null);
				customer.setTherapeutist(null);
				customer.setUpdateuser(null);
				customerMapper.insertSelective(customer);
				//------根据订单ID查询被转诊用户之前的项目信息
				TCustomerConsumptonAmountExample ccaexample = new TCustomerConsumptonAmountExample();
				ccaexample.createCriteria().andCustomerIdEqualTo(orderid);
				List<TCustomerConsumptonAmount> ccaclist = consumptonAmountMapper.selectByExample(ccaexample);
				for (TCustomerConsumptonAmount ccac : ccaclist) {
					Integer ccacid = ccac.getId();
					//TODO 查询项目修改项目为对应公司的项目ID
					TProject project = projectMapper.selectByPrimaryKey(ccac.getProjectId().longValue());
					TProjectExample proexample = new TProjectExample();
					proexample.createCriteria().andCompanyIdEqualTo(companyId1).andProjectNameEqualTo(project.getProjectName()).andProjectModelEqualTo(project.getProjectModel());
					List<TProject> prolist = projectMapper.selectByExample(proexample);
					if(prolist.size()>0) {
						TProject project2 = prolist.get(0);
						ccac.setProjectId(project2.getId().intValue());
					}
					ccac.setId(null);
					ccac.setCustomerId(customer.getId());
					ccac.setCreateuser(null);
					ccac.setUpdateuser(null);
					consumptonAmountMapper.insertSelective(ccac);
					//TODO 查询产品修改产品为转诊门店的产品ID
					TConsumptonProjectExample examplecp = new TConsumptonProjectExample();
					examplecp.createCriteria().andConsumptonAmountIdEqualTo(ccacid);
					List<TConsumptonProject> listcp = consumptonProjectMapper.selectByExample(examplecp);
					for (TConsumptonProject cp : listcp) {
						cp.setConsumptonAmountId(ccac.getId());
						cp.setCreateuser(null);
						cp.setUpdateuser(null);
						cp.setId(null);
						consumptonProjectMapper.insertSelective(cp);
					}
				}
			}
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】转诊操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}
}
