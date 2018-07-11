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
import com.gasq.bdp.logn.model.TCustomerPorject;
import com.gasq.bdp.logn.model.TCustomerProjectLog;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;
import com.gasq.bdp.logn.service.CustomerService;
import com.gasq.bdp.logn.service.TCustomerProjectService;
import com.gasq.bdp.logn.service.TVipCustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/customerProject")
@Api("用户项目接口API")
public class CustomerProjectController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TCustomerProjectService customerProjectService;
    @Autowired CustomerService customerService;
    @Autowired TVipCustomerService vipCustomerService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表用户项目信息列表", notes="查询列表用户项目信息列表")
    @ApiImplicitParam(name = "bean", value = "sql实体对象TCustomerPorject", required = false, dataType = "TCustomerPorject")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryHives(TCustomerPorject bean) {
		try {
			return customerProjectService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新用户项目信息", notes="添加或更新用户项目信息（管理员、操作用户、测试用户）")
    @ApiImplicitParam(name = "bean", value = "sql实体对象TCustomerPorject", required = true, dataType = "TCustomerPorject")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCustomerPorject bean) {
		try {
			return customerProjectService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除用户项目信息", notes="删除用户项目信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "sql实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(Integer id) {
		try {
			return customerProjectService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="查询列表用户消费套餐信息列表", notes="查询列表用户消费套餐信息列表")
    @ApiImplicitParam(name = "bean", value = "实体对象TLtnCustomerConsumptonAmount", required = false, dataType = "TLtnCustomerConsumptonAmount")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
	@RequestMapping(value = "/queryCustomerAmountListByCustomerPhone",method=RequestMethod.POST)
	public Map<String, Object> queryCustomerAmountListByCustomerPhone(TLtnCustomer bean) {
		try {
			TVipCustomerExample example = new TVipCustomerExample();
			example.createCriteria().andCustomerPhoneEqualTo(bean.getPhonenumb()).andStatusEqualTo(1);
			List<TVipCustomer> list = vipCustomerService.selectByExample(example);
			TCustomerPorject ccbean = new TCustomerPorject();
			if(list.size()>0) {
				ccbean.setVipId(list.get(0).getId());
			}else {
				ccbean.setVipId(-1);
			}
			return customerProjectService.queryPagingList(ccbean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @ApiOperation(value="刷卡结算用户项目信息", notes="刷卡结算用户项目信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/swipingCardCommit",method=RequestMethod.POST)
	public Map<String,Object> swipingCardCommit(Integer id) {
		try {
			return customerProjectService.swipingCardCommit(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    @ApiOperation(value="刷卡结算记录用户项目信息", notes="刷卡结算记录用户项目信息（管理员、操作用户）")
    @ApiImplicitParam(name = "id", value = "实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/queryCustomerProjectLogs",method=RequestMethod.POST)
	public Map<String,Object> queryCustomerProjectLogs(TCustomerProjectLog customerProjectLog) {
		try {
			return customerProjectService.queryCustomerProjectLogs(customerProjectLog);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
    
}

	
	
