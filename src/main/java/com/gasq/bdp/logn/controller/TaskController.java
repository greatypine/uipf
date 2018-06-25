package com.gasq.bdp.logn.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TSysMenu;
import com.gasq.bdp.logn.model.TSysMenuExample;
import com.gasq.bdp.logn.service.TSysMenuService;
import com.gasq.bdp.logn.utils.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/task")
@Api("菜单配置接口API")
public class TaskController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysMenuService menuService;
    
  /**
	 * 加载菜单
	 * @return
	 */
    @ApiOperation(value="查询菜单列表", notes="查询菜单列表（所有用户）")
    @ApiImplicitParam(name = "pid", value = "菜单对象<TSysMenu>实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryMenus")
	public List<TSysMenu> queryMenus(int pid) {
		if(CommonUtils.isEmpty(pid)) return null;
		TSysMenuExample menu = new TSysMenuExample();
		menu.createCriteria().andParendidEqualTo(pid);
		List<TSysMenu> menus = menuService.selectByExample(menu);
		return menus;
	 }
}

	
	
