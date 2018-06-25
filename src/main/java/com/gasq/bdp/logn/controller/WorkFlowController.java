package com.gasq.bdp.logn.controller;


import java.util.ArrayList;
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
import com.gasq.bdp.logn.model.TSysTimerWorkflow;
import com.gasq.bdp.logn.service.TSysTimerWorkflowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/workflow")
@Api("更新字段的Key配置接口API")
public class WorkFlowController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerWorkflowService workflowService;
    
    @ApiOperation(value="分页查询列表更新字段的Key配置信息列表", notes="查询列表更新字段的Key配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段的Key实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryTree",method=RequestMethod.POST)
	public List<Map<String, Object>> queryTree(Integer pid) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		Map<String, Object> tree=workflowService.queryWorkFlowTree(pid);
		trees.add(tree);
    	return trees;
	 }
    
    @ApiOperation(value="分页查询列表更新字段的Key配置信息列表", notes="查询列表更新字段的Key配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段的Key实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public Boolean saveOrUpdate(TSysTimerWorkflow workflow) {
		Boolean b = false;
		try {
			b = workflowService.saveOrUpdate(workflow);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return b;
	 }
    
    @ApiOperation(value="添加或更新更新字段的Key配置信息", notes="添加或更新更新字段的Key配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段的Key实体对象TSysSqlUpdateColumns", required = true, dataType = "TSysSqlUpdateColumns")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public Boolean saveOrUpdate(int id) {
		Boolean b = false;
		try {
			b = workflowService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return b;
	 }
	
	@ApiOperation(value="分页查询列表更新字段的Key配置信息列表", notes="查询列表更新字段的Key配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段的Key实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/getWorkflowJobList",method=RequestMethod.POST)
	public List<Map<String, Object>> getWorkflowList(Integer typeid,Integer jobid) {
		List<Map<String, Object>> result =workflowService.getWorkflowJobList(typeid,jobid);
    	return result;
	 }
	
	@ApiOperation(value="分页查询列表更新字段的Key配置信息列表", notes="查询列表更新字段的Key配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段的Key实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/getWorkflowJobType",method=RequestMethod.GET)
	public List<Map<String, Object>> getWorkflowJobType(Integer typeid) {
		List<Map<String, Object>> result =workflowService.getWorkflowJobType(typeid);
    	return result;
	 }
	
	@ApiOperation(value="分页查询列表更新字段的Key配置信息列表", notes="查询列表更新字段的Key配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "更新字段的Key实体对象TSysSqlUpdateColumns", required = false, dataType = "TSysSqlUpdateColumns")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryWorkflowViewList",method=RequestMethod.POST)
	public List<TSysTimerWorkflow> queryList(TSysTimerWorkflow workflow) {
		List<TSysTimerWorkflow> result =workflowService.queryList(workflow);
    	return result;
	 }
	
}

	
	
