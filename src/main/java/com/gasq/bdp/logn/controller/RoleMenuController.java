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
import com.gasq.bdp.logn.service.TSysRoleMenuService;
import com.gasq.bdp.logn.utils.CommonUtils;

@RestController
@RequestMapping(value = "/rolemenu")
public class RoleMenuController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysRoleMenuService roleMenuService;
    
    /**
	 * 加载菜单
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public Boolean saveOrUpdate(String menuss,Integer roleid) {
		if(roleid == null) return false;
		List<Map> menus = CommonUtils.json2List(menuss, Map.class);
		return roleMenuService.saveOrUpdate(roleid,menus);
	 }
}

	
	
