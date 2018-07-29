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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.service.TInventoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/inventory")
@Api("库存配置接口API")
public class InventoryController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TInventoryService inventoryService;
    //错误信息
    Map<String,Object> map = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表库存配置信息列表", notes="查询列表库存配置信息列表")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TInventory bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表库存配置信息列表！");
		try {
			map = inventoryService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表库存配置信息列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询库存对象列表", notes="查询库存对象列表")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TInventory> queryBeanList(TInventory bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询库存对象列表！");
		try {
			List<TInventory> list = inventoryService.selectByExample(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询库存对象列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return list;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新库存配置信息", notes="添加或更新库存配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = true, dataType = "TInventory")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TInventory bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求添加或更新库存配置信息！");
		try {
			Boolean f = inventoryService.saveOrUpdate(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求添加或更新库存配置信息结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除库存配置信息", notes="删除库存配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "库存实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除库存配置信息！");
		try {
			Boolean f = inventoryService.delete(id);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除库存配置信息结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    
    @ApiOperation(value="查询当前库存", notes="查询当前库存")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/syncInventory",method=RequestMethod.POST)
	public Double syncInventory(Integer id) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询当前库存！");
		try {
			Double d =inventoryService.getInventoryNumbs(id);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询当前库存结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return d;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询当前商品是否有库存", notes="查询当前商品是否有库存")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/checkInventoryEnable",method=RequestMethod.POST)
	public Boolean checkInventoryEnable(Integer id,Double numbs) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询当前商品是否有库存！");
		try {
			Boolean f = inventoryService.checkInventoryEnable(id,numbs);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询当前商品是否有库存结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return f;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="入库", notes="产品入库")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = true, dataType = "TInventory")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/appendInventory",method=RequestMethod.POST)
	public Map<String, Object> appendInventory(TInventory bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求产品入库！");
		try {
			map = inventoryService.appendInventory(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求产品入库结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    @ApiOperation(value="出库", notes="产品出库")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = true, dataType = "TInventory")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/deliveryInventory",method=RequestMethod.POST)
	public Map<String, Object> deliveryInventory(TInventory bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求产品出库！");
		try {
			map = inventoryService.deliveryInventory(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求产品出库结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
}

	
	
