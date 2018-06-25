package com.gasq.bdp.logn.controller;

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
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.service.CommonService;

@RestController
@RequestMapping(value = "/common")
public class CommonController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired CommonService commonService;
    
	/**
	 * 用户来源
	 */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryRootIn",method=RequestMethod.POST)
	public List<Map<String,Object>> queryRootIn(TCompany bean) {
		try {
			return commonService.queryRootIn(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/querySex",method=RequestMethod.POST)
	public List<Map<String,Object>> querySex(TCompany bean) {
		try {
			return commonService.querySex(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    /**
     * 用户状态
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryUserStatus",method=RequestMethod.POST)
	public List<Map<String,Object>> queryUserStatus(TCompany bean) {
		try {
			return commonService.queryUserStatus(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    /**
     * 查询系统美容师
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryCosmetologist",method=RequestMethod.POST)
	public List<Map<String,Object>> queryCosmetologist(TCompany bean) {
		try {
			return commonService.queryCosmetologist(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    /**
     * 查询系统咨询师
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryCounsoler",method=RequestMethod.POST)
	public List<Map<String,Object>> queryCounsoler(TCompany bean) {
		try {
			return commonService.queryCounsoler(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    /**
     * 查询产品
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryProjectInventory",method=RequestMethod.POST)
	public List<Map<String,Object>> queryProjectInventory(TCompany bean) {
		try {
			return commonService.queryProjectInventory(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/getView",method=RequestMethod.POST)
	public List<Map<String,Object>> getView(TCompany bean) {
		try {
			return commonService.getView(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
}

	
	
