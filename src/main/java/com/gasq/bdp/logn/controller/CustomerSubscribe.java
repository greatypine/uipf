package com.gasq.bdp.logn.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.service.CustomerSubscribeService;

@RestController
@RequestMapping(value = "/customerSubscribe")
public class CustomerSubscribe {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired CustomerSubscribeService customerSubscribeService;
    //错误信息
    Map<String,Object> map = new HashMap<String,Object>();
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION,RoleSign.Q_COUNELOR },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TCustomerSubscribe bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求预约列表！");
		try {
			map = customerSubscribeService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求预约列表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TCustomerSubscribe> queryBeanList(TCustomerSubscribe bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求预约对象列表！");
		try {
			return customerSubscribeService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCustomerSubscribe bean) {
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求新增或修改预约信息！");
		try {
			return customerSubscribeService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除预约信息！");
		try {
			return customerSubscribeService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
