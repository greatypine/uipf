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
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.service.TCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/company")
@Api("公司配置接口API")
public class CompanyController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TCompanyService companyService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表公司配置信息列表", notes="查询列表公司配置信息列表（所以）")
    @ApiImplicitParam(name = "bean", value = "公司实体对象TCompany", required = false, dataType = "TCompany")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TCompany bean) {
		try {
			return companyService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_RECEPTIONIST },logical=Logical.OR)
    @RequestMapping(value = "/queryMapBeanList",method=RequestMethod.POST)
	public List<Map<String,Object>> queryMapBeanList(TCompany bean) {
		try {
			return companyService.queryMapBeanList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="添加或更新公司配置信息", notes="添加或更新公司配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "公司实体对象TCompany", required = true, dataType = "TCompany")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TCompany bean) {
		try {
			return companyService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
    @ApiOperation(value="删除公司配置信息", notes="删除公司配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "公司实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return companyService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
