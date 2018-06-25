package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.service.TSysTimerJobSqlQueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/sqlquery")
@Api("sqlquery配置接口API")
public class SqlQueryController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerJobSqlQueryService sqlqueryService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表sqlquery配置信息列表", notes="查询列表sqlquery配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "sqlquery实体对象TSysTimerJobSqlQuery", required = false, dataType = "TSysTimerJobSqlQuery")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/querySqls",method=RequestMethod.POST)
	public Map<String, Object> queryHives(TSysTimerJobSqlQuery bean) {
		try {
			return sqlqueryService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新sqlquery配置信息", notes="添加或更新sqlquery配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "sqlquery实体对象TSysTimerJobSqlQuery", required = true, dataType = "TSysTimerJobSqlQuery")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysTimerJobSqlQuery bean) {
		try {
			return sqlqueryService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除sqlquery配置信息", notes="删除sqlquery配置信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "sqlquery实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(Integer id) {
		try {
			return sqlqueryService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
