package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.CustomerSubscribeService;

@RestController
@RequestMapping(value = "/customerSubscribe")
public class CustomerSubscribe {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired CustomerSubscribeService customerSubscribeService;
    //错误信息
    Map<String,Object> map = new HashMap<String,Object>();
    
    @Ilogger(value="预约列表")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION,RoleSign.Q_COUNELOR },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TCustomerSubscribe bean) {
		try {
			map = customerSubscribeService.queryPagingList(bean);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="预约对象列表")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TCustomerSubscribe> queryBeanList(TCustomerSubscribe bean) {
		try {
			return customerSubscribeService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="新增或修改预约信息")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCustomerSubscribe bean) {
		try {
			return customerSubscribeService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="删除预约信息")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(int id) {
		try {
			return customerSubscribeService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="请求预约接诊信息")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION,RoleSign.Q_COUNELOR },logical=Logical.OR)
	@RequestMapping(value = "/querySubscribeReceptionInfo",method=RequestMethod.POST)
	public Map<String, Object> querySubscribeReceptionInfo() {
		try {
			String info = customerSubscribeService.querySubscribeReceptionInfo();
			if(StringUtils.isBlank(info)) return null;
			map.put("mess", info);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    @Ilogger(value="请求预约信息")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION,RoleSign.Q_COUNELOR },logical=Logical.OR)
	@RequestMapping(value = "/querySubscribeInfo",method=RequestMethod.POST)
	public Map<String, Object> queryReceptionInfo() {
		try {
			String info = customerSubscribeService.querySubscribeInfo();
			if(StringUtils.isBlank(info)) return null;
			map.put("mess", info);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
}

	
	
