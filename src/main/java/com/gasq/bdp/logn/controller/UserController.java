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
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.TSysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/systemuser")
@Api(value="用户controller",tags={"用户管理接口"})
public class UserController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TSysUserService sysUserService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    @Ilogger(value="查询列表用户配置信息列表")
    @ApiOperation(value="查询列表用户配置信息列表", notes="查询列表用户配置信息列表")
    @ApiImplicitParam(name = "bean", value = "用户实体对象TSysUser", required = false, dataType = "TSysUser")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TSysUser bean) {
		try {
			return sysUserService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="查询用户对象列表")
    @ApiOperation(value="查询用户对象列表", notes="查询用户对象列表")
    @ApiImplicitParam(name = "bean", value = "用户实体对象TSysUser", required = false, dataType = "TSysUser")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TSysUser> queryBeanList(TSysUser bean) {
		try {
			return sysUserService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="添加或更新用户配置信息")
    @ApiOperation(value="添加或更新用户配置信息", notes="添加或更新用户配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "用户实体对象TSysUser", required = true, dataType = "TSysUser")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public Map<String,Object> saveOrUpdate(TSysUser bean) {
		try {
			if(bean.getId()==null) {
				boolean c = sysUserService.checkUserNameEable(bean.getUsername());
				if(!c) {
					logger.info("用户名[" + bean.getUsername() + "]已经存在请重新设置用户名！");
					paramMap.put("status", false);
					paramMap.put("mess", "用户《"+bean.getUsername()+"》已经存在请重新设置用户名。");
					return paramMap;
				}
			}
			int x = sysUserService.saveOrUpdate(bean);
			if(x==0) {
				paramMap.put("status", true);
			}else if(x==1) {
				paramMap.put("status", false);
				paramMap.put("mess", "用户《"+bean.getUsername()+"》已经存在！请确认后重新操作。");
			}else{
				paramMap.put("status", false);
				paramMap.put("mess", "操作失败请稍后再次操作。");
			}
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return paramMap;
	 }
    @Ilogger(value="删除用户配置信息")
    @ApiOperation(value="删除用户配置信息", notes="删除用户配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "用户实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return sysUserService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="锁屏解锁")
    @ApiOperation(value="锁屏解锁", notes="锁屏解锁")
    @ApiImplicitParam(name = "id", value = "用户实体id", required = true, dataType = "Integer", paramType="query")
	@RequestMapping(value = "/unlockSubmit",method=RequestMethod.POST)
	public boolean unlockSubmit(TSysUser bean) {
		try {
			return sysUserService.unlockSubmit(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="查询用户对象列表")
    @ApiOperation(value="查询用户对象列表", notes="查询用户对象列表")
    @ApiImplicitParam(name = "bean", value = "用户实体对象TSysUser", required = false, dataType = "TSysUser")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/getSysUserInfo",method=RequestMethod.POST)
	public TSysUser getSysUserInfo(Long id) {
		try {
			return sysUserService.getSysUserInfo(id);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="修改用户密码")
    @ApiOperation(value="修改用户密码", notes="修改用户密码")
	@RequestMapping(value = "/changePassword",method=RequestMethod.POST)
	public Map<String,Object> changePassword(String oldpwd,String newpwd,String snewpwd) {
		try {
			return sysUserService.changePassword(oldpwd,newpwd);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
}

	
	
