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
import com.gasq.bdp.logn.model.TSysBasecode;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.BaseCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/basecode")
@Api("基础编码配置接口API")
public class BaseCodeController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired BaseCodeService baseCodeService;
    //错误信息
    Map<String,Object> map = new HashMap<String,Object>();
    
    @Ilogger(value="查询基础编码列表")
    @ApiOperation(value="查询列表基础编码配置信息列表", notes="查询列表基础编码配置信息列表")
    @ApiImplicitParam(name = "bean", value = "基础编码实体对象TSysBasecode", required = false, dataType = "TSysBasecode")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryList(TSysBasecode bean) {
		try {
			map = baseCodeService.queryPagingList(bean);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @Ilogger(value="添加或更新基础编码配置信息")
    @ApiOperation(value="添加或更新基础编码配置信息", notes="添加或更新基础编码配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "基础编码实体对象TSysBasecode", required = true, dataType = "TSysBasecode")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysBasecode bean) {
		try {
			return baseCodeService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @Ilogger(value="删除基础编码配置信息")
    @ApiOperation(value="删除基础编码配置信息", notes="删除基础编码配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "基础编码实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return baseCodeService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
