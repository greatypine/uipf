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
import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.service.UpdateColumnsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/updatecolumns")
@Api("更新字段配置接口API")
public class UpdateColumnsController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    UpdateColumnsService updateColumnsService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    
    @ApiOperation(value="分页查询列表更新字段配置信息列表", notes="查询列表更新字段配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryPagingList",method=RequestMethod.POST)
	public Map<String, Object> queryPagingList(TSysSqlUpdateColumns bean) {
		try {
			return updateColumnsService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询列表更新字段配置信息列表", notes="查询列表更新字段配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public List<Map<String, Object>> queryList(TSysSqlUpdateColumns bean) {
		try {
			return updateColumnsService.queryList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
	
	@ApiOperation(value="添加或更新更新字段配置信息", notes="添加或更新更新字段配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段实体对象TSysSqlUpdateColumns", required = true, dataType = "TSysSqlUpdateColumns")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysSqlUpdateColumns bean) {
		try {
			return updateColumnsService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	
	@ApiOperation(value="删除更新字段配置信息", notes="删除更新字段配置信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "更新字段实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(Integer id) {
		try {
			return updateColumnsService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
