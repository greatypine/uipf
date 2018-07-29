package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.service.TVipCustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/vipcustomer")
@Api("会员用户配置接口API")
public class VipCustomerController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TVipCustomerService vipCustomerService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表会员用户配置信息列表", notes="查询列表会员用户配置信息列表")
    @ApiImplicitParam(name = "bean", value = "会员用户实体对象TVipCustomer", required = false, dataType = "TVipCustomer")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TVipCustomer bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表会员用户配置信息列表！");
		try {
			return vipCustomerService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新会员用户配置信息", notes="添加或更新会员用户配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "会员用户实体对象TVipCustomer", required = true, dataType = "TVipCustomer")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(@Valid TVipCustomer bean,BindingResult bindingResult) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求添加或更新会员用户配置信息！");
		try {
			return vipCustomerService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除会员用户配置信息", notes="删除会员用户配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "会员用户实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除会员用户配置信息！");
		try {
			return vipCustomerService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="发送消息", notes="发送消息")
    @ApiImplicitParam(name = "bean", value = "会员用户实体对象TVipCustomer", required = false, dataType = "TMessage")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/sendMessage",method=RequestMethod.POST)
	public Map<String, Object> sendMessage(TVipCustomer bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求发送消息！");
		try {
			return vipCustomerService.sendMessage(bean);
		}catch (Exception e) {
			paramMap.put("status", false);
			paramMap.put("mess", "消息发送失败！");
			logger.info(e.getMessage(),e);
		}
    	return paramMap;
	 }
    @ApiOperation(value="根据会员信息快速生成消费订单信息", notes="根据会员信息快速生成消费订单信息")
    @ApiImplicitParam(name = "bean", value = "会员用户实体对象TVipCustomer", required = true, dataType = "TVipCustomer")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/createConsumptonOrder",method=RequestMethod.POST)
	public boolean createConsumptonOrder(TVipCustomer bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】开始请求根据会员信息快速生成消费订单信息！");
		try {
			return vipCustomerService.createConsumptonOrder(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
}

	
	
