package com.gasq.bdp.logn.controller;

import java.time.Duration;
import java.time.Instant;
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
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TEquipment;
import com.gasq.bdp.logn.service.TEquipmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/equipment")
@Api("设备配置接口API")
public class EquipmentController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TEquipmentService equipmentService;
    //错误信息
    Map<String,Object> map = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表设备配置信息列表", notes="查询列表设备配置信息列表")
    @ApiImplicitParam(name = "bean", value = "设备实体对象TEquipment", required = false, dataType = "TEquipment")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryList(TEquipment bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表设备配置信息列表！");
		try {
			map = equipmentService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表设备配置信息列表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新设备配置信息", notes="添加或更新设备配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "设备实体对象TEquipment", required = false, dataType = "TEquipment")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TEquipment bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求添加或更新设备配置信息！");
		try {
			return equipmentService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除设备配置信息", notes="删除设备配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "设备实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除设备配置信息！");
		try {
			return equipmentService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @ApiOperation(value="查询列表设备总金额", notes="查询列表设备总金额")
    @ApiImplicitParam(name = "bean", value = "设备实体对象TEquipment", required = false, dataType = "TEquipment")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryAmountSum",method=RequestMethod.POST)
	public Map<String, Object> queryAmountSum(TEquipment bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询列表设备总金额！");
		try {
			return equipmentService.queryAmountSum(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
}

	
	
