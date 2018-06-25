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
import com.gasq.bdp.logn.model.TSysData;
import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.service.TSysDataColumnsService;
import com.gasq.bdp.logn.service.TSysDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/data")
@Api("数据对象接口API")
public class DataController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysDataService dataService;
    @Autowired
    TSysDataColumnsService dataColumnsService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询数据对象列表", notes="查询数据对象列表")
    @ApiImplicitParam(name = "bean", value = "数据实体对象TSysData", required = false, dataType = "TSysData")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/queryDatas",method=RequestMethod.POST)
	public List<TSysData> queryList(TSysData bean) {
		try {
			return dataService.queryList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询数据类型", notes="查询数据类型")
    @RequestMapping(value = "/getDateType",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public List<Map<String, Object>> getDateType() {
		try {
			return dataService.getDateType();
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="查询列表", notes="根据导入配置对象查询配置列表")
    @ApiImplicitParam(name = "bean", value = "导入实体对象TSysTimerJobDataImport", required = false, dataType = "TSysTimerJobDataImport")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/queryDataTree",method=RequestMethod.POST)
	public List<Map<String, Object>> queryDataTree(TSysData bean) {
		try {
			return dataService.queryDataTree(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="查询数据行对象树", notes="根据数据对象查询数据行对象树")
    @ApiImplicitParam(name = "bean", value = "数据行实体对象TSysData", required = false, dataType = "TSysData")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/queryDataColumnsTree",method=RequestMethod.POST)
	public List<Map<String, Object>> queryDataColumnsTree(TSysData bean) {
		try {
			return dataService.queryDataColumnsTree(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="查询数据对象列表", notes="查询数据对象列表")
    @ApiImplicitParam(name = "bean", value = "数据实体对象TSysData", required = false, dataType = "TSysData")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/queryDataList",method=RequestMethod.POST)
	public List<TSysData> queryDataList(TSysData bean) {
		try {
			return dataService.queryList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="分页查询数据对象列表", notes="分页查询数据对象列表")
    @ApiImplicitParam(name = "bean", value = "数据实体对象TSysData", required = false, dataType = "TSysData")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryDataPaging",method=RequestMethod.POST)
	public Map<String, Object> queryPagingList(TSysData bean) {
		try {
			return dataService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="保存或更新数据对象", notes="保存或更新数据对象")
    @ApiImplicitParam(name = "bean", value = "数据实体对象TSysData", required = true, dataType = "TSysData")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdateData",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysData bean) {
		try {
			return dataService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除数据对象配置信息", notes="删除数据对象配置信息（同时会删除数据对象下的行信息）")
    @ApiImplicitParam(name = "id", value = "数据实体对象id", required = true, dataType = "Integer", paramType="query")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/deleteData",method=RequestMethod.GET)
	public boolean delete(Integer id) {
		try {
			return dataService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="查询数据行对象列表", notes="查询数据行对象列表")
    @ApiImplicitParam(name = "bean", value = "数据实体对象TSysDataColumns", required = false, dataType = "TSysDataColumns")
 	@RequestMapping(value = "/queryDataColumnsList",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public List<TSysDataColumns> queryDataList(TSysDataColumns bean) {
		try {
			return dataColumnsService.queryList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="查询列表行数据列表", notes="查询列表行数据列表")
    @ApiImplicitParam(name = "bean", value = "行实体对象TSysDataColumns", required = false, dataType = "TSysDataColumns")
	@RequestMapping(value = "/queryDataColumns",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public Map<String, Object> queryDataColumns(TSysDataColumns bean) {
		try {
			return dataColumnsService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="保存或更新行数据", notes="保存或更新行数据")
    @ApiImplicitParam(name = "bean", value = "行数据实体对象TSysDataColumns", required = true, dataType = "TSysDataColumns")
	@RequestMapping(value = "/saveOrUpdateDataColumns",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public boolean saveOrUpdate(TSysDataColumns bean) {
		try {
			return dataColumnsService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除行", notes="根据对象ID删除行")
    @ApiImplicitParam(name = "id", value = "实体对象ID", required = true, dataType = "Integer", paramType="query")
	@RequestMapping(value = "/deleteColumns",method=RequestMethod.GET)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	public boolean deleteColumns(Integer id) {
		try {
			return dataColumnsService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	
    @ApiOperation(value="查询比较符号列表", notes="查询比较符号列表")
	@RequestMapping(value = "/getCompareSymbol",method=RequestMethod.GET)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public List<Map<String,Object>> getCompareSymbol() {
		try {
			return dataColumnsService.getCompareSymbol();
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
}

	
	
