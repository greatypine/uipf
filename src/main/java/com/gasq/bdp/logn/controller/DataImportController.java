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
import com.gasq.bdp.logn.model.TSysTimerJobDataImport;
import com.gasq.bdp.logn.service.TSysTimerJobDataImportService;

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
@RequestMapping("dataimport")
@Api("数据导入接口API")
public class DataImportController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerJobDataImportService dataImportService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="根据导入配置对象查询配置列表", notes="根据导入配置对象查询配置列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "导入实体对象TSysTimerJobDataImport", required = false, dataType = "TSysTimerJobDataImport")
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	public Map<String, Object> queryList(TSysTimerJobDataImport bean) {
		try {
			return dataImportService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @ApiOperation(value="添加或更新导入配置信息", notes="添加或更新导入配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "导入实体对象TSysTimerJobDataImport", required = true, dataType = "TSysTimerJobDataImport")
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	public boolean saveOrUpdate(TSysTimerJobDataImport bean) {
		try {
			return dataImportService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除导入配置信息", notes="删除导入配置信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "导入实体id", required = true, dataType = "Integer", paramType="query")
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN},logical=Logical.OR)
	public boolean delete(Integer id) {
		try {
			return dataImportService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
