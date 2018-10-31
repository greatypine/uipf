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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TTherapistTreatmentTimeInfo;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.TTherapistTreatmentTimeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author Ju_weigang
 * @时间 2018年10月8日下午5:39:16
 * @项目路径 com.gasq.bdp.logn.controller
 * @描述 
 */
@RestController
@RequestMapping(value = "/ttti")
@Api("治疗师治疗时间段")
public class TherapistTreatmentTimeInfoController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TTherapistTreatmentTimeInfoService treatmentTimeInfoService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @Ilogger(value="治疗师治疗时间段列表")
    @ApiOperation(value="查询治疗师治疗时间段信息列表", notes="查询治疗师治疗时间段信息列表")
    @ApiImplicitParam(name = "bean", value = "治疗师治疗时间段对象TTherapistTreatmentTimeInfo", required = false, dataType = "TTherapistTreatmentTimeInfo")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryZLSList",method=RequestMethod.POST)
	public List<Map<String, Object>> queryZLSList(TTherapistTreatmentTimeInfo bean) {
		try {
			return treatmentTimeInfoService.queryZLSList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="查询预约日志对象列表")
    @ApiOperation(value="查询预约日志对象列表", notes="查询预约日志对象列表")
    @ApiImplicitParam(name = "bean", value = "预约日志实体对象TTherapistTreatmentTimeInfo", required = false, dataType = "TTherapistTreatmentTimeInfo")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryBeanList",method=RequestMethod.POST)
	public List<TTherapistTreatmentTimeInfo> queryBeanList(TTherapistTreatmentTimeInfo bean) {
		try {
			return treatmentTimeInfoService.selectByExample(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger(value="插入或更新预约日志")
    @ApiOperation(value="添加或更新预约日志配置信息", notes="添加或更新预约日志配置信息（管理员、操作、测试）")
    @ApiImplicitParam(name = "bean", value = "预约日志实体对象TTherapistTreatmentTimeInfo", required = true, dataType = "TTherapistTreatmentTimeInfo")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TTherapistTreatmentTimeInfo bean) {
		try {
			return treatmentTimeInfoService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    @Ilogger(value="删除预约日志")
    @ApiOperation(value="删除预约日志配置信息", notes="删除预约日志配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "预约日志实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(int id) {
		try {
			return treatmentTimeInfoService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}
