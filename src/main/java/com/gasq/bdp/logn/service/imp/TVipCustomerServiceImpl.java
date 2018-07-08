/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TLtnCustomerConsumptonAmountMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerLogMapper;
import com.gasq.bdp.logn.mapper.TVipCustomerMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.model.TVipCustomerLog;
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
	public Map<String, Object> queryMapGridList(TVipCustomer bean) {
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
		list = mapper.queryMapGridList(bean);
		if(list==null || list.size()<=0)list = new ArrayList<Map<String,Object>>(); 
		result.put("rows",list);
		result.put("total",list.size());
		return result;
	}

	@Override
	public List<Map<String, Object>> queryMapGridChildren(TVipCustomer bean) {
		TLtnCustomerConsumptonAmount tca = new TLtnCustomerConsumptonAmount();
		if(bean.getId()!=null) {
			tca.setCustomerId(bean.getId());
		}
//		tca.setCompanyid(SystemUserInfo.getSystemUser().getUser().getCompanyid());
		List<Map<String, Object>> children = mapper.queryMapGridChildren(tca);
		return children;
	}
}
