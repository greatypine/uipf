package com.gasq.bdp.logn.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TConsumptonProject;
import com.gasq.bdp.logn.service.TConsumptonProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/consumptonProject")
@Api("消费产品配置接口API")
public class ConsumptonProjectController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TConsumptonProjectService consumptonProjectService;
	 
 	@ApiOperation(value="查询列表消费产品配置信息列表", notes="查询列表消费产品配置信息列表")
    @ApiImplicitParam(name = "bean", value = "消费产品实体对象TConsumptonProject", required = false, dataType = "TConsumptonProject")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(@Valid TConsumptonProject bean) {
 		Instant start = Instant.now();
 		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询列表消费产品配置信息列表！");
		try {
			Map<String, Object> map = consumptonProjectService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊数据列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询消费产品对象列表", notes="查询消费产品对象列表")
    @ApiImplicitParam(name = "bean", value = "消费产品实体对象TConsumptonProject", required = false, dataType = "TConsumptonProject")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TConsumptonProject> queryBeanList(TConsumptonProject bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询消费产品对象列表！");
		try {
			return consumptonProjectService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新消费产品配置信息", notes="添加或更新消费产品配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "消费产品实体对象TConsumptonProject", required = true, dataType = "TConsumptonProject")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(TConsumptonProject bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加或更新消费产品配置信息！");
		try {
			return consumptonProjectService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="删除消费产品配置信息", notes="删除消费产品配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "消费产品实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除消费产品配置信息！");
		try {
			return consumptonProjectService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}