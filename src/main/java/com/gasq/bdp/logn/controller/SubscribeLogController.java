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
import com.gasq.bdp.logn.model.TCustomerSubscribeLog;
import com.gasq.bdp.logn.service.SubscribeLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/subscribelog")
@Api("预约日志配置接口API")
public class SubscribeLogController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired SubscribeLogService subscribeLogService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表预约日志配置信息列表", notes="查询列表预约日志配置信息列表")
    @ApiImplicitParam(name = "bean", value = "预约日志实体对象TCustomerSubscribeLog", required = false, dataType = "TCustomerSubscribeLog")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TCustomerSubscribeLog bean) {
		try {
			return subscribeLogService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询预约日志对象列表", notes="查询预约日志对象列表")
    @ApiImplicitParam(name = "bean", value = "预约日志实体对象TCustomerSubscribeLog", required = false, dataType = "TCustomerSubscribeLog")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TCustomerSubscribeLog> queryBeanList(TCustomerSubscribeLog bean) {
		try {
			return subscribeLogService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新预约日志配置信息", notes="添加或更新预约日志配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "预约日志实体对象TCustomerSubscribeLog", required = true, dataType = "TCustomerSubscribeLog")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCustomerSubscribeLog bean) {
		try {
			return subscribeLogService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除预约日志配置信息", notes="删除预约日志配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "预约日志实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
		try {
			return subscribeLogService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
