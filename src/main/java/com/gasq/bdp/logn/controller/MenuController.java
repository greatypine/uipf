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
import com.gasq.bdp.logn.model.TSysMenu;
import com.gasq.bdp.logn.service.TSysMenuService;
import com.gasq.bdp.logn.utils.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/menu")
@Api(value="菜单controller",tags={"菜单管理接口"})
public class MenuController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysMenuService menuService;
    
    /**
	 * 加载菜单
	 * @return
	 */
    @ApiOperation(value="查询菜单列表", notes="查询菜单列表（所有用户）")
    @ApiImplicitParam(name = "pid", value = "菜单对象<TSysMenu>实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryMenus",method=RequestMethod.POST)
	public List<TSysMenu> queryMenus(Integer pid) {
		if(CommonUtils.isEmpty(pid)) return null;
		List<TSysMenu> menus = menuService.selectMenuBeanByRole(pid);
		return menus;
	 }
    
    /**
	 * 加载角色菜单树
	 * @return
	 */
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN},logical=Logical.OR)
	@RequestMapping(value = "/selectAllMenusTreeByRole",method=RequestMethod.POST)
	public List<Map<String, Object>> selectAllMenusTreeByRole(Integer roleid) {
		return menuService.selectAllMenusTreeByRole(roleid);
	 }
    
    /**
   	 * 加载菜单
   	 * @return
   	 */
    @ApiOperation(value="查询菜单列表", notes="查询菜单列表（所有用户）")
    @ApiImplicitParam(name = "pid", value = "菜单对象<TSysMenu>实体pid", required = true, dataType = "Integer", paramType="query")
   	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
   	@RequestMapping(value = "/queryAllMenus",method=RequestMethod.POST)
   	public List<Map<String, Object>> queryAllMenus(Integer pid,Integer roleid) {
   		if(pid==null) return null;
   		List<Map<String, Object>> menus = menuService.selectAllMenusByRole(pid,roleid);
   		return menus;
   	 }
    
    @ApiOperation(value="查询列表菜单配置信息列表", notes="查询列表菜单配置信息列表（所有）")
    @ApiImplicitParam(name = "bean", value = "菜单实体对象TSysMenu", required = false, dataType = "TSysMenu")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TSysMenu bean) {
		try {
			return menuService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @ApiOperation(value="添加或更新菜单配置信息", notes="添加或更新菜单配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "菜单实体对象TSysMenu", required = true, dataType = "TSysMenu")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysMenu bean) {
		try {
			return menuService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除菜单配置信息", notes="删除菜单配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "菜单实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return menuService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
