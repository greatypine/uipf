package com.gasq.bdp.logn.controller;

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
import com.gasq.bdp.logn.model.TMessage;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.TMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/message")
@Api("消息配置接口API")
public class MessageController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TMessageService messageService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @Ilogger(value="查询列表消息配置信息列表")
    @ApiOperation(value="查询列表消息配置信息列表", notes="查询列表消息配置信息列表")
    @ApiImplicitParam(name = "bean", value = "消息实体对象TMessage", required = false, dataType = "TMessage")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TMessage bean) {
		try {
			return messageService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @Ilogger(value="添加或更新消息配置信息")
    @ApiOperation(value="添加或更新消息配置信息", notes="添加或更新消息配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "消息实体对象TMessage", required = true, dataType = "TMessage")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TMessage bean) {
		try {
			return messageService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @Ilogger(value="删除消息配置信息")
    @ApiOperation(value="删除消息配置信息", notes="删除消息配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "消息实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return messageService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
