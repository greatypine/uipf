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
import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.provider.Ilogger;
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
    
    @Ilogger(value="查询列表库存配置信息列表")
    @ApiOperation(value="查询列表库存配置信息列表", notes="查询列表库存配置信息列表")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TInventory bean) {
		try {
			map = inventoryService.queryPagingList(bean);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="查询库存对象列表")
    @ApiOperation(value="查询库存对象列表", notes="查询库存对象列表")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TInventory> queryBeanList(TInventory bean) {
		try {
			List<TInventory> list = inventoryService.selectByExample(bean);
			return list;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="添加或更新库存配置信息")
    @ApiOperation(value="添加或更新库存配置信息", notes="添加或更新库存配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = true, dataType = "TInventory")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TInventory bean) {
		try {
			Boolean f = inventoryService.saveOrUpdate(bean);
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="删除库存配置信息")
    @ApiOperation(value="删除库存配置信息", notes="删除库存配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "库存实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
		try {
			Boolean f = inventoryService.delete(id);
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @Ilogger(value="查询当前库存")
    @ApiOperation(value="查询当前库存", notes="查询当前库存")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/syncInventory",method=RequestMethod.POST)
	public Double syncInventory(Integer id) {
		try {
			Double d =inventoryService.getInventoryNumbs(id);
			return d;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="查询当前商品是否有库存")
    @ApiOperation(value="查询当前商品是否有库存", notes="查询当前商品是否有库存")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/checkInventoryEnable",method=RequestMethod.POST)
	public Boolean checkInventoryEnable(Integer id,Double numbs) {
		try {
			Boolean f = inventoryService.checkInventoryEnable(id,numbs);
			return f;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="产品入库")
    @ApiOperation(value="入库", notes="产品入库")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = true, dataType = "TInventory")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/appendInventory",method=RequestMethod.POST)
	public Map<String, Object> appendInventory(TInventory bean) {
		try {
			map = inventoryService.appendInventory(bean);
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    @Ilogger(value="产品出库")
    @ApiOperation(value="出库", notes="产品出库")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = true, dataType = "TInventory")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/deliveryInventory",method=RequestMethod.POST)
	public Map<String, Object> deliveryInventory(TInventory bean) {
		try {
			map = inventoryService.deliveryInventory(bean);
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
}

	
	
