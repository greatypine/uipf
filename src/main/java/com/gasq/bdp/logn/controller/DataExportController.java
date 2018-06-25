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
import com.gasq.bdp.logn.model.TSysTimerJobDataExport;
import com.gasq.bdp.logn.service.TSysTimerJobDataExportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.controller
 * @creatTime 2017年12月20日下午5:21:49
 * @remark
 */
@RestController
@RequestMapping("dataexport")
@Api("数据导出接口API")
public class DataExportController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerJobDataExportService dataExportService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表", notes="根据导出配置对象查询配置列表")
    @ApiImplicitParam(name = "bean", value = "导出实体对象TSysTimerJobDataExport", required = false, dataType = "TSysTimerJobDataExport")
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public Map<String, Object> queryList(TSysTimerJobDataExport bean) {
		try {
			return dataExportService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return paramMap;
	 }
    
    @ApiOperation(value="保存或更新导出配置信息", notes="保存或更新导出配置信息")
    @ApiImplicitParam(name = "bean", value = "导出实体对象TSysTimerJobDataExport", required = true, dataType = "TSysTimerJobDataExport")
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	public boolean saveOrUpdate(TSysTimerJobDataExport bean) {
		try {
			return dataExportService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除导出配置信息", notes="删除导出配置信息")
    @ApiImplicitParam(name = "id", value = "导出实体对象id", required = true, dataType = "Integer",paramType="query")
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN},logical=Logical.OR)
	public boolean delete(Integer id) {
		try {
			return dataExportService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	
    @ApiOperation(value="查询导出配置类型列表", notes="查询导出配置类型列表")
	@RequestMapping(value = "/queryExprotType",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public List<Map<String, Object>> queryExprotType() {
		try {
			return dataExportService.queryExprotType();
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
}

	
	
