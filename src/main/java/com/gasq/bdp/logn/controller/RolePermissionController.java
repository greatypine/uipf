package com.gasq.bdp.logn.controller;

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
import com.gasq.bdp.logn.model.TSysRolePermission;
import com.gasq.bdp.logn.service.TSysRolePermissionService;

@RestController
@RequestMapping(value = "/rolepermission")
public class RolePermissionController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TSysRolePermissionService roleService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TSysRolePermission bean) {
		try {
			return roleService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TSysRolePermission> queryBeanList(TSysRolePermission bean) {
		try {
			return roleService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/queryMapBeanList",method=RequestMethod.POST)
	public List<Map<String,Object>> queryMapBeanList(TSysRolePermission bean) {
		try {
			return roleService.queryMapBeanList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysRolePermission bean) {
		try {
			return roleService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return roleService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
