/**
 * 
 */
package com.gasq.bdp.logn.controller;

import java.time.Duration;
import java.time.Instant;
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
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.model.WorkForceParams;
import com.gasq.bdp.logn.service.WorkforceManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午2:10:08
 * @项目路径 com.gasq.bdp.logn.controller
 * @描述 
 */
@RestController
@RequestMapping(value = "/workforceManagement")
@Api("排班接口API")
public class WorkforceManagement {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired WorkforceManagementService workforceManagementService;
	
	Map<String,Object> map = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表排班信息列表", notes="查询列表排班信息列表")
    @ApiImplicitParam(name = "bean", value = "公司实体对象TWorkforcemanagement", required = false, dataType = "TWorkforcemanagement")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryList(TWorkforcemanagement bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】开始查询公司列表！");
		try {
			map = workforceManagementService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊数据列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
	
    @ApiOperation(value="添加或更新排班信息", notes="添加或更新排班信息")
    @ApiImplicitParam(name = "bean", value = "公司实体对象TWorkforcemanagement", required = true, dataType = "TWorkforcemanagement")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(@RequestBody List<TWorkforcemanagement> bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】开始添加或更新排班信息！");
		try {
			return workforceManagementService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="添加或更新排班信息", notes="添加或更新排班信息")
    @ApiImplicitParam(name = "bean", value = "公司实体对象WorkForceParams", required = true, dataType = "WorkForceParams")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/addwf",method=RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(WorkForceParams bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】开始添加或更新排班信息！");
		try {
			return workforceManagementService.addwf(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="删除排班信息", notes="删除排班信息")
    @ApiImplicitParam(name = "id", value = "公司实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(TWorkforcemanagement bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除排班信息！");
		try {
			return workforceManagementService.delete(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}
