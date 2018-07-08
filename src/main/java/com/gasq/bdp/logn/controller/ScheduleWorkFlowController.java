package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflow;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowExample;
import com.gasq.bdp.logn.model.TSysTimerScheduleWorkflowKey;
import com.gasq.bdp.logn.service.TSysTimerScheduleWorkflowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/scheduleWorkflow")
@Api("定时器工作流配置接口API")
public class ScheduleWorkFlowController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerScheduleWorkflowService scheduleWorkflowService;
    
    @ApiOperation(value="查询定时器工作流列表", notes="查询定时器工作流列表")
    @ApiImplicitParam(name = "scheduleId", value = "定时器实体对象id", required = true, dataType = "Integer",paramType="body")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/getlist",method=RequestMethod.POST)
	public Map<String, Object> getlist(Integer scheduleId) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysTimerScheduleWorkflowExample example = new TSysTimerScheduleWorkflowExample();
		example.createCriteria().andScheduleIdEqualTo(scheduleId);
		example.setOrderByClause("sort asc");
		int totals=(int)scheduleWorkflowService.countByExample(example);
		if(totals>0) {
			List<TSysTimerScheduleWorkflow> list=scheduleWorkflowService.selectByExample(example);
			result.put("rows", list);
			result.put("total", totals);
		}else {
			result.put("rows", "");
			result.put("total", 1);
		}
    	return result;
	 }
	
    @ApiOperation(value="批量保存或更新定时器工作流配置信息", notes="批量保存或更新定时器工作流配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "定时器实体对象scheduleWorkflows", required = true, dataType = "TSysTimerScheduleWorkflow")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/batchsaveOrUpdate",method=RequestMethod.POST)
	public int batchsaveOrUpdate(@RequestBody List<TSysTimerScheduleWorkflow> scheduleWorkflows) {
		int totals = 0;
		if(scheduleWorkflows.size()>0) {
			for (TSysTimerScheduleWorkflow tSysTimerScheduleWorkflow : scheduleWorkflows) {
				totals= scheduleWorkflowService.insert(tSysTimerScheduleWorkflow);
				if(totals<=0) return -1;
			}
		}
    	return totals;
	 }
	
    @ApiOperation(value="查询定时器工作流信息", notes="查询定时器工作流信息（所有用户）")
    @ApiImplicitParam(name = "bean", value = "定时器工作流key实体对象TSysTimerScheduleWorkflowKey", required = true, dataType = "TSysTimerScheduleWorkflowKey")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/getScheduleWorkflowById",method=RequestMethod.POST)
	public Map<String, Object> getScheduleWorkflowById(TSysTimerScheduleWorkflowKey key) {
		Map<String, Object> result = scheduleWorkflowService.getScheduleWorkflowById(key);
    	return result;
	 }
	
    @ApiOperation(value="删除定时器工作流", notes="删除定时器工作流（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "定时器工作流KEY实体对象TSysTimerScheduleWorkflowKey", required = true, dataType = "TSysTimerScheduleWorkflowKey")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value="/removeById",method=RequestMethod.POST)
    public int removeById(TSysTimerScheduleWorkflowKey key){
    	try {
			return scheduleWorkflowService.deleteByPrimaryKey(key);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return 0;
    }
	
    @ApiOperation(value="改变工作流状态", notes="改变工作流状态（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "定时器工作流实体对象TSysTimerScheduleWorkflow", required = true, dataType = "TSysTimerScheduleWorkflow")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value="/changeWorkflowStatus",method=RequestMethod.POST)
    public int changeWorkflowStatus(TSysTimerScheduleWorkflow scheduleWorkflow){
    	try {
			return scheduleWorkflowService.changeWorkflowStatus(scheduleWorkflow);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return 0;
    }
	
    @ApiOperation(value="改变工作流排序", notes="改变工作流排序（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "定时器工作流实体对象TSysTimerScheduleWorkflow", required = true, dataType = "TSysTimerScheduleWorkflow")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value="/changeWorkflowSort",method=RequestMethod.POST)
    public int changeWorkflowSort(TSysTimerScheduleWorkflow scheduleWorkflow){
    	try {
			return scheduleWorkflowService.changeWorkflowSort(scheduleWorkflow);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return 0;
    }
	
}

	
	
