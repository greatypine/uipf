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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.mapper.TLtnCustomerConsumptonAmountMapper;
import com.gasq.bdp.logn.mapper.TMessageMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerLogMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TMessage;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.model.TVipCustomerExample.Criteria;
import com.gasq.bdp.logn.model.TVipCustomerLog;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.service.TVipCustomerService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年10月31日上午11:33:35
 * @remark 
 */
@Service
public class TVipCustomerServiceImpl implements TVipCustomerService {
	@Autowired TVipCustomerMapper mapper;
	@Autowired TVipCustomerLogMapper customerLogMapper;
	@Autowired TLtnCustomerConsumptonAmountMapper consumptonAmountMapper;
	@Autowired TMessageMapper messagemapper;
	@Autowired EmailManager emailManager;
	
	@Value("${spring.mail.username}")
	private String sourceEmail;
	
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
		List<Map<String,Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		bean.setPage(start);
		bean.setRows(number);
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
		list = mapper.queryPagingList(bean);
		if(list==null || list.size()<=0)list = new ArrayList<Map<String,Object>>(); 
		Integer count = mapper.countByBean(bean);
		result.put("rows",list);
		result.put("total",count);
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
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
					emailManager.sendSimpleEmail(sourceEmail, emails, message.getTitle(), message.getMessage());
				}else if(bean.getType()==2) {//短信
					
				}
			}
			result.put("status", true);
			result.put("mess", "发送消息成功！共发送消息【"+emails.length+"】条！");
		} catch (Exception e) {
			throw new WorkFlowJobException("发送消息失败！"+e.getMessage(),e);
		}
		return result;
	}
}
