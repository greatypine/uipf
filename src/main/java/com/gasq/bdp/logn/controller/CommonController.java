package com.gasq.bdp.logn.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gasq.bdp.logn.mapper.TSysUserExtMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExt;
import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.CustomerSubscribeService;
import com.gasq.bdp.logn.service.TCustomerImagesService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;

@RestController
@RequestMapping(value = "/common")
public class CommonController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired CommonService commonService;
	@Autowired TCustomerImagesService customerImagesService;
	@Autowired TSysUserExtMapper sysUserExtMapper;
	@Autowired CustomerSubscribeService subscribeService;
	/**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${img.location}")
    private String location;
    
    /**
     * 客户图片上传
     */
	@PostMapping("/img/upload")
    public Map<String,Object> uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile,TCustomerImages customerImages)  {
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】开始上传图片！");
		Map<String,Object> markDVo = new HashMap<>();
    	markDVo.put("status",false);
    	if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())|| customerImages==null) {
           logger.error("文件或者订单信息为空！");
           markDVo.put("mess", "文件或者订单信息为空！");
           return markDVo;
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            logger.error("图片格式不正确！");
            markDVo.put("mess", "图片格式不正确！");
            return markDVo;
        }
        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
        TSysUser currentUser = SystemUserInfo.getSystemUser().getUser();
        //获取路径
        String return_path = currentUser.getCompanyid()+File.separator+customerImages.getCustomerPhone()+ File.separator +customerImages.getOrderId();
        String filePath = location+return_path;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;
        String postfix = ".png";
        try {
            file_name = CommonUtils.saveImg(multipartFile, filePath,postfix);
            if(StringUtils.isNotBlank(file_name)){
            	markDVo.put("status",true);
            	markDVo.put("mess","上传成功");
            	markDVo.put("url",return_path+File.separator+file_name);
            	customerImages.setImgUrl(return_path+File.separator+file_name);
            	customerImagesService.saveOrUpdate(customerImages);
            }else {
            	markDVo.put("mess","上传失败");
            }
            logger.info("返回值：{}",markDVo);
            return markDVo;
        } catch (IOException e) {
            logger.error("保存图片失败！"+e.getMessage(),e);
            markDVo.put("mess", "保存图片失败！");
            return markDVo;
        }
    }
	/**
	 * 系统用户图片上传
	 */
	@PostMapping("/userimg/upload")
    public Map<String,Object> uploadUserImg(@RequestParam("editormd-image-file") MultipartFile multipartFile,TSysUser sysUser)  {
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】开始上传图片！");
		Map<String,Object> markDVo = new HashMap<>();
    	markDVo.put("status",false);
    	if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())|| sysUser==null) {
           logger.error("文件或者订单信息为空！");
           markDVo.put("mess", "文件或者订单信息为空！");
           return markDVo;
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            logger.error("图片格式不正确！");
            markDVo.put("mess", "图片格式不正确！");
            return markDVo;
        }
        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
        TSysUser currentUser = SystemUserInfo.getSystemUser().getUser();
        //获取路径
        String return_path = "userimgs"+File.separator+currentUser.getCompanyid();
        String filePath = location+return_path;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;
        String postfix = ".jpg";
        try {
            file_name = CommonUtils.saveSysUserImg(multipartFile, filePath,sysUser.getUsername(),postfix);
            if(StringUtils.isNotBlank(file_name)){
            	markDVo.put("status",true);
            	markDVo.put("mess","上传成功");
            	markDVo.put("url",return_path+File.separator+file_name);
            	TSysUserExt sysExtUser = new TSysUserExt();
            	sysExtUser.setUserId(sysUser.getId());
            	sysExtUser.setImagePath(return_path+File.separator+file_name);
            	sysUserExtMapper.updateByPrimaryKeySelective(sysExtUser);
            }else {
            	markDVo.put("mess","上传失败");
            }
            logger.info("返回值：{}",markDVo);
            return markDVo;
        } catch (IOException e) {
            logger.error("保存图片失败！"+e.getMessage(),e);
            markDVo.put("mess", "保存图片失败！");
            return markDVo;
        }
    }
	/**
	 * 用户来源
	 */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryRootIn",method=RequestMethod.POST)
	public List<Map<String,Object>> queryRootIn(TCompany bean) {
		try {
			return commonService.queryRootIn(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/querySex",method=RequestMethod.POST)
	public List<Map<String,Object>> querySex(TCompany bean) {
		try {
			return commonService.querySex(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    /**
     * 用户状态
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryUserStatus",method=RequestMethod.POST)
	public List<Map<String,Object>> queryUserStatus(TCompany bean) {
		try {
			return commonService.queryUserStatus(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    /**
     * 查询系统美容师
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryCosmetologist",method=RequestMethod.POST)
	public List<Map<String,Object>> queryCosmetologist(TCompany bean) {
		try {
			return commonService.queryCosmetologist(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    /**
     * 查询系统咨询师
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryCounsoler",method=RequestMethod.POST)
	public List<Map<String,Object>> queryCounsoler(TCompany bean) {
		try {
			return commonService.queryCounsoler(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    /**
     * 查询产品
     */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryProjectInventory",method=RequestMethod.POST)
	public List<Map<String,Object>> queryProjectInventory(TCompany bean) {
		try {
			return commonService.queryProjectInventory(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/getView",method=RequestMethod.POST)
	public List<Map<String,Object>> getView(TCompany bean) {
		try {
			return commonService.getView(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计员工订单接诊报表数据")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryEmployeeTreatOrderReport",method=RequestMethod.POST)
	public Map<String, Object> queryEmployeeTreatOrderReport(Integer type,Integer companyid,String datetype,String starttime,String endtime) {
		try {
			Map<String, Object> map = commonService.queryEmployeeTreatOrderReport(type,companyid,datetype,starttime,endtime);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计员工订单接诊数据列表")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryEmployeeTreatOrderDataDetail",method=RequestMethod.POST)
	public Map<String, Object> queryEmployeeTreatOrderDataDetail(Integer type,Integer companyid,String datetype,String starttime,String endtime,Integer page,Integer rows) {
    	try {
			Map<String, Object> map = commonService.queryEmployeeTreatOrderDataDetail(type,companyid,datetype,starttime,endtime,page,rows);
			return map;
    	}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计员工订单报表数据")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test },logical=Logical.OR)
    @RequestMapping(value = "/queryBackEmployeeOrderReport",method=RequestMethod.POST)
	public Map<String, Object> queryBackEmployeeOrderReport(Integer type,Integer companyid,String datetype,String starttime,String endtime) {
    	try {
			Map<String, Object> map = commonService.queryBackEmployeeOrderReport(type,companyid,datetype,starttime,endtime);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计员工订单数据列表")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test },logical=Logical.OR)
    @RequestMapping(value = "/queryBackEmployeeOrderDataDetail",method=RequestMethod.POST)
	public Map<String, Object> queryBackEmployeeOrderDataDetail(Integer type,Integer companyid,String datetype,String starttime,String endtime,Integer page,Integer rows) {
    	try {
			Map<String, Object> map = commonService.queryBackEmployeeOrderDataDetail(type,companyid,datetype,starttime,endtime,page,rows);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计库存数据")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryCountInventory",method=RequestMethod.POST)
	public Map<String, Object> queryCountInventory(Integer companyid,String datetype,Integer year,Integer month,Integer page,Integer rows) {
		try {
			Map<String, Object> map = commonService.queryCountInventory(companyid,datetype,year,month,page,rows);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计库存饼图")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryCountInventoryPie",method=RequestMethod.POST)
	public Map<String, Object> queryCountInventoryPie(Integer companyid,String datetype,Integer year,Integer month) {
		try {
			Map<String, Object> map = commonService.queryCountInventoryPie(companyid,datetype,year,month);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计商业报表列表")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR },logical=Logical.OR)
    @RequestMapping(value = "/queryCountBusinessAnalysisDataDetail",method=RequestMethod.POST)
	public Map<String, Object> queryCountBusinessAnalysisDataDetail(Integer type,Integer companyid,String datetype,String starttime,String endtime,Integer page,Integer rows) {
    	try {
			Map<String, Object> map = commonService.queryCountBusinessAnalysisDataDetail(type, companyid, datetype, starttime, endtime, page, rows);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询门店统报表列表")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER},logical=Logical.OR)
    @RequestMapping(value = "/queryStoreReport",method=RequestMethod.POST)
	public Map<String, Object> queryStoreReport(Integer companyid,String endtime,Integer page,Integer rows) {
    	try {
			Map<String, Object> map = commonService.queryStoreReport(companyid,endtime, page, rows);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("首页前台系统用户信息统计")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR },logical=Logical.OR)
    @RequestMapping(value = "/queryIndexUserCount",method=RequestMethod.POST)
	public Map<String, Object> queryIndexUserCount() {
    	try {
			Map<String, Object> map = commonService.queryIndexUserCount();
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("首页前台门店信息统计")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR },logical=Logical.OR)
    @RequestMapping(value = "/queryIndexCompanyCount",method=RequestMethod.POST)
	public Map<String, Object> queryIndexCompanyCount() {
    	try {
			Map<String, Object> map = commonService.queryIndexCompanyCount();
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("首页前台门店排班信息统计")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_ADMIN,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR},logical=Logical.OR)
    @RequestMapping(value = "/queryWorkforceList",method=RequestMethod.POST)
	public Map<String, Object> queryWorkforceList() {
    	try {
    		TWorkforcemanagement bean = new TWorkforcemanagement();
    		bean.setCycle(DateUtil.getDateStr(DateUtil.getSysCurrentDate(), DateUtil.DATE_TIME_FLAG_NO_DATE_FORMAT));
    		bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
			Map<String, Object> map = commonService.queryWorkforceList(bean);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("首页前台门预约息统计")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_OPTION,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_ADMIN,RoleSign.H_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/querySubscribeList",method=RequestMethod.POST)
	public List<TCustomerSubscribe> querySubscribeList() {
    	try {
			List<TCustomerSubscribe> map = subscribeService.querySubscribeList();
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计项目类型数据")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test },logical=Logical.OR)
    @RequestMapping(value = "/queryProjectTypeReport",method=RequestMethod.POST)
	public Map<String, Object> queryProjectTypeReport(Integer companyid,String starttime,String endtime) {
    	try {
			Map<String, Object> map = commonService.queryProjectTypeReport(companyid,starttime,endtime);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @Ilogger("查询统计项目类型转化数据")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.Q_COUNELOR,RoleSign.Test },logical=Logical.OR)
    @RequestMapping(value = "/queryProjectTypeChangeReport",method=RequestMethod.POST)
	public Map<String, Object> queryProjectTypeChangeReport(Integer companyid,String starttime,String endtime) {
    	try {
			Map<String, Object> map = commonService.queryProjectTypeChangeReport(companyid,starttime,endtime);
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
}