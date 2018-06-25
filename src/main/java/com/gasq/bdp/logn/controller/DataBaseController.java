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
import com.gasq.bdp.logn.model.TSysDatasource;
import com.gasq.bdp.logn.service.TSysDatasourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/db")
@Api("数据库对象接口API")
public class DataBaseController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysDatasourceService datasourceService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询数据库对象列表", notes="查询数据库对象列表")
    @ApiImplicitParam(name = "bean", value = "数据库实体对象TSysDatasource", required = false, dataType = "TSysDatasource")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryDataBases",method=RequestMethod.POST)
	public Map<String, Object> queryDataBases(TSysDatasource bean) {
		try {
			return datasourceService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @ApiOperation(value="查询数据库对象树", notes="查询数据库对象树")
    @ApiImplicitParam(name = "bean", value = "数据库实体对象TSysDatasource", required = false, dataType = "TSysDatasource")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryDataBaseTree",method=RequestMethod.POST)
	public List<Map<String, Object>> queryDataBaseTree(TSysDatasource bean) {
		List<Map<String, Object>> trees = null;
		try {
			trees =  datasourceService.queryDataBaseTree(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return trees;
	 }
    
    @ApiOperation(value="保存或更新数据库对象", notes="保存或更新数据库对象")
    @ApiImplicitParam(name = "bean", value = "数据库实体对象TSysDatasource", required = true, dataType = "TSysDatasource")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysDatasource bean) {
		try {
			return datasourceService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除数据库对象配置信息", notes="删除数据库对象配置信息（同时会删除数据库对象下的行信息）")
    @ApiImplicitParam(name = "id", value = "数据库实体对象id", required = true, dataType = "Integer", paramType="query")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(Integer id) {
		try {
			return datasourceService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @ApiOperation(value="检查数据库连接", notes="检查数据库连接")
    @ApiImplicitParam(name = "bean", value = "数据库实体对象TSysDatasource", required = true, dataType = "TSysDatasource")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/check",method=RequestMethod.POST)
	public boolean check(TSysDatasource bean) {
		try {
			return datasourceService.check(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @ApiOperation(value="根据表查询对应的字段", notes="根据表查询对应的字段（所有用户）")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "dbcode", value = "数据库实体对象code", required = true, dataType = "String", paramType="query"),
    	@ApiImplicitParam(name = "tablename", value = "数据库实体对象表名", required = true, dataType = "String", paramType="query")
    })
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/getColumnsByTable",method=RequestMethod.POST)
	public List<Map<String,Object>> getColumnsByTable(String dbcode,String tablename) {
		try {
			return datasourceService.getColumnsByTable(dbcode,tablename);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="查询数据中的表", notes="查询数据中的表（所有用户）")
    @ApiImplicitParam(name = "dbcode", value = "数据库实体对象code", required = true, dataType = "String", paramType="query")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryTablesByDb",method=RequestMethod.POST)
	public List<Map<String,Object>> queryTablesByDb(String dbcode) {
		try {
			return datasourceService.queryTablesByDb(dbcode);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
	
}

	
	
