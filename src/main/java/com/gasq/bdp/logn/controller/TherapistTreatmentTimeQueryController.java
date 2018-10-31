/**
 * 
 */
package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQuery;
import com.gasq.bdp.logn.model.WorkForceParams;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.TherapistTreatmentTimeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午2:10:08
 * @项目路径 com.gasq.bdp.logn.controller
 * @描述 
 */
@RestController
@RequestMapping(value = "/tttq")
@Api("治疗师排班接口API")
public class TherapistTreatmentTimeQueryController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TherapistTreatmentTimeQueryService treatmentTimeQueryService;
	
	Map<String,Object> map = new HashMap<String,Object>();
    
	@Ilogger(value="查询公司列表")
    @ApiOperation(value="查询列表治疗师排班信息列表", notes="查询列表治疗师排班信息列表")
    @ApiImplicitParam(name = "bean", value = "公司实体对象TTherapistTreatmentTimeQuery", required = false, dataType = "TTherapistTreatmentTimeQuery")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryList(TTherapistTreatmentTimeQuery bean) {
		try {
			map = treatmentTimeQueryService.queryPagingList(bean);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
	@Ilogger(value="添加或更新治疗师排班信息")
    @ApiOperation(value="添加或更新治疗师排班信息", notes="添加或更新治疗师排班信息")
    @ApiImplicitParam(name = "bean", value = "公司实体对象TTherapistTreatmentTimeQuery", required = true, dataType = "TTherapistTreatmentTimeQuery")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(@RequestBody List<TTherapistTreatmentTimeQuery> bean) {
		try {
			return treatmentTimeQueryService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	@Ilogger(value="添加或更新治疗师排班信息")
    @ApiOperation(value="添加或更新治疗师排班信息", notes="添加或更新治疗师排班信息")
    @ApiImplicitParam(name = "bean", value = "公司实体对象WorkForceParams", required = true, dataType = "WorkForceParams")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/addwf",method=RequestMethod.POST)
	public Map<String, Object> saveOrUpdate(WorkForceParams bean) {
		try {
			return treatmentTimeQueryService.addwf(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
	@Ilogger(value="删除治疗师排班信息")
    @ApiOperation(value="删除治疗师排班信息", notes="删除治疗师排班信息")
    @ApiImplicitParam(name = "id", value = "公司实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(TTherapistTreatmentTimeQuery bean) {
		try {
			return treatmentTimeQueryService.delete(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}
