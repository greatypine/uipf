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
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.service.TSysTimerJobSqlInsertOrUpdateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/sqlInsertOrUpdate")
@Api("sqlInsertOrUpdate配置接口API")
public class SqlInsertOrUpdateController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerJobSqlInsertOrUpdateService insertOrUpdateService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表sqlInsertOrUpdate配置信息列表", notes="查询列表sqlInsertOrUpdate配置信息列表")
    @ApiImplicitParam(name = "bean", value = "sqlInsertOrUpdate实体对象TSysTimerJobSql", required = false, dataType = "TSysTimerJobSql")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/querySqlInsertOrUpdates",method=RequestMethod.POST)
	public Map<String, Object> queryHives(TSysTimerJobSqlInsertUpdate bean) {
		try {
			return insertOrUpdateService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新sqlInsertOrUpdate配置信息", notes="添加或更新sqlInsertOrUpdate配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "sqlInsertOrUpdate实体对象TSysTimerJobSql", required = true, dataType = "TSysTimerJobSql")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysTimerJobSqlInsertUpdate bean) {
		try {
			return insertOrUpdateService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除sqlInsertOrUpdate配置信息", notes="删除sqlInsertOrUpdate配置信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "sqlInsertOrUpdate实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(Integer id) {
		try {
			return insertOrUpdateService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @ApiOperation(value="删除sqlInsertOrUpdate配置信息", notes="删除sqlInsertOrUpdate配置信息（管理员、操作用户）")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "baseData", value = "插入或更新对象<TSysTimerJobSqlInsertUpdate>json格式", required = true, dataType = "String",paramType="query"),
		@ApiImplicitParam(name = "keys", value = "数据key结合<TSysSqlUpdateKeys>json格式", required = true, dataType = "String",paramType="query"),
		@ApiImplicitParam(name = "columns", value = "数据行结合<TSysSqlUpdateColumns>json格式", required = true, dataType = "String",paramType="query")
    })
	@RequestMapping(value = "/saveAll",method=RequestMethod.POST)
	public boolean saveAll(String baseData,String keys,String columns) {
		try {
			return insertOrUpdateService.saveAll(baseData,keys,columns);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
