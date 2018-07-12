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
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表库存配置信息列表", notes="查询列表库存配置信息列表")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TInventory bean) {
		try {
			return inventoryService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询库存对象列表", notes="查询库存对象列表")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TInventory> queryBeanList(TInventory bean) {
		try {
			return inventoryService.selectByExample(bean);
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
		try {
			return inventoryService.saveOrUpdate(bean);
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
		try {
			return inventoryService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    
    @ApiOperation(value="查询当前库存", notes="查询当前库存")
    @ApiImplicitParam(name = "bean", value = "库存实体对象TInventory", required = false, dataType = "TInventory")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/syncInventory",method=RequestMethod.POST)
	public Double syncInventory(Integer id) {
		try {
			return inventoryService.getInventoryNumbs(id);
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
		try {
			return inventoryService.checkInventoryEnable(id,numbs);
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
		try {
			return inventoryService.appendInventory(bean);
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
		try {
			return inventoryService.deliveryInventory(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
}

	
	
