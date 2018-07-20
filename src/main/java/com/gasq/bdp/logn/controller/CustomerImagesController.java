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
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.service.TCustomerImagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/customerImages")
@Api("客户图片配置接口API")
public class CustomerImagesController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TCustomerImagesService CustomerImagesService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询客户图片对象列表", notes="查询客户图片对象列表")
    @ApiImplicitParam(name = "bean", value = "客户图片实体对象TCustomerImages", required = false, dataType = "TCustomerImages")
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TCustomerImages> queryBeanList(TCustomerImages bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询客户图片对象列表！");
		try {
			return CustomerImagesService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新客户图片配置信息", notes="添加或更新客户图片配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "客户图片实体对象TCustomerImages", required = true, dataType = "TCustomerImages")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCustomerImages bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求添加或更新客户图片！");
		try {
			return CustomerImagesService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除客户图片配置信息", notes="删除客户图片配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "客户图片实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
		try {
			return CustomerImagesService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="求和客户图片数量", notes="求和客户图片数量")
    @ApiImplicitParam(name = "id", value = "客户图片实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/getCount",method=RequestMethod.POST)
	public Integer getCount(TCustomerImages bean) {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求求和客户图片数量！");
		try {
			return CustomerImagesService.getCount(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return 0;
	 }
}

	
	
