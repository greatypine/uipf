package com.gasq.bdp.logn.controller;

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
import com.gasq.bdp.logn.model.TCustomerProjectLog;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.TCustomerProjectLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/cpl")
@Api("刷卡消费日志配置接口API")
public class CustomerProjectLogController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TCustomerProjectLogService customerProjectLogService;
    
    @Ilogger(value="查询列表刷卡消费日志配置信息列表")
    @ApiOperation(value="查询列表刷卡消费日志配置信息列表", notes="查询列表刷卡消费日志配置信息列表")
    @ApiImplicitParam(name = "bean", value = "刷卡消费日志实体对象TcustomerProjectLogService", required = false, dataType = "TcustomerProjectLogService")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryList(TCustomerProjectLog bean) {
		try {
			return customerProjectLogService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="查询刷卡消费日志对象列表")
    @ApiOperation(value="查询刷卡消费日志对象列表", notes="查询刷卡消费日志对象列表")
    @ApiImplicitParam(name = "bean", value = "刷卡消费日志实体对象TcustomerProjectLogService", required = false, dataType = "TcustomerProjectLogService")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TCustomerProjectLog> queryBeanList(TCustomerProjectLog bean) {
		try {
			return customerProjectLogService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="添加或更新刷卡消费日志配置信息")
    @ApiOperation(value="添加或更新刷卡消费日志配置信息", notes="添加或更新刷卡消费日志配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "刷卡消费日志实体对象TcustomerProjectLogService", required = true, dataType = "TcustomerProjectLogService")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCustomerProjectLog bean) {
		try {
			return customerProjectLogService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="删除刷卡消费日志配置信息")
    @ApiOperation(value="删除刷卡消费日志配置信息", notes="删除刷卡消费日志配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "刷卡消费日志实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
		try {
			return customerProjectLogService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
