package com.gasq.bdp.logn.controller;

import java.util.ArrayList;
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
import com.gasq.bdp.logn.job.InterfaceType;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfig;
import com.gasq.bdp.logn.service.TSysTimerScheduleconfigService;
import com.gasq.bdp.logn.state.StateContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/schedule")
@Api("定时器配置接口API")
public class ScheduleController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerScheduleconfigService scheduleconfigService;
    @Autowired StateContext stateContext;
    
    /**
	 * 加载菜单
	 * @return
	 */
    @ApiOperation(value="查询列表定时器配置信息列表", notes="查询列表定时器配置信息列表（所以用户）")
    @ApiImplicitParam(name = "bean", value = "定时器实体对象TSysTimerLog", required = false, dataType = "TSysTimerLog")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/getList",method=RequestMethod.POST)
    public Map<String, Object> timmerdata(TSysTimerScheduleconfig scheduleconfig,int page,int rows){
    	Map<String, Object> result= new  HashMap<String, Object>();
    	int totals = 0;
		try {
			totals = (int)scheduleconfigService.selectScheduleCount(scheduleconfig);
		} catch (Exception e1) {
			logger.debug(e1.getMessage());
		}
    	int start = 0;
    	List<TSysTimerScheduleconfig> list= null;
    	if(totals>0) {
    		int intPage = ( page==0) ? 1 : page;
    		int number = (rows==0) ? 10 : rows;
    		start = (intPage - 1) * number;
    		try {
				list=scheduleconfigService.selectByExamplePaging(scheduleconfig, start, rows);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
    	}else {
    		list= new ArrayList<TSysTimerScheduleconfig>();
    	}
    	result.put("rows", list);
    	result.put("total", totals);
    	return result;
    }
    
    @ApiOperation(value="添加或更新定时器配置信息", notes="添加或更新定时器配置信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "定时器实体对象TSysTimerScheduleconfig", required = true, dataType = "TSysTimerScheduleconfig")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
    public int saveOrUpdate(TSysTimerScheduleconfig scheduleconfig){
    	try {
			return scheduleconfigService.saveOrUpdate(scheduleconfig);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return 0;
    }
    
    @ApiOperation(value="删除定时器配置信息", notes="删除定时器配置信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "定时器实体id", required = true, dataType = "Long", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
    @RequestMapping(value="/removeById",method=RequestMethod.GET)
    public int removeById(Long id){
    	try {
			return scheduleconfigService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return 0;
    }
    
    @ApiOperation(value="手动执行定时器", notes="手动执行定时器（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "定时器实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
    @RequestMapping(value="/manualExecute",method=RequestMethod.POST)
    public Map<String,Object> manualExecute(Integer id){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
			 boolean b = scheduleconfigService.manualExecute(id);
			 if(b) {
				 result.put("status",true);
				 result.put("mess","操作成功！");
			 }else {
				 result.put("status",false);
				 result.put("mess","操作失败！");
			 }
		} catch (Exception e) {
			result.put("status",false);
			result.put("mess","操作失败！错误信息--》"+e.getMessage());
			logger.debug(e.getMessage());
		}
    	return result;
    }
    
    @ApiOperation(value="查询工作流状态", notes="查询工作流状态（所有用户）")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
    @RequestMapping(value="/getWorkFlowStatus",method=RequestMethod.GET)
    public Map<String,Object> getWorkFlowStatus(){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
    		result.put("mess", InterfaceType.getValueName(stateContext.getExetype())+stateContext.getTaskname()+stateContext.getStatusName());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			result.put("mess", "执行失败");
		}
    	return result;
    }
    
    @ApiOperation(value="查询定时器树", notes="查询定时器树（所有用户）")
    @ApiImplicitParam(name = "bean", value = "定时器实体对象TSysTimerScheduleconfig", required = true, dataType = "TSysTimerScheduleconfig")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryScheduleTree",method=RequestMethod.POST)
	public List<Map<String, Object>> queryScheduleTree(TSysTimerScheduleconfig bean) {
		List<Map<String, Object>> trees = null;
		try {
			trees =  scheduleconfigService.queryScheduleTree(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return trees;
	 }
}