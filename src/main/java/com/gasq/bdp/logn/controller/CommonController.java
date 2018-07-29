package com.gasq.bdp.logn.controller;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
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

import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.TCustomerImagesService;
import com.gasq.bdp.logn.utils.CommonUtils;

@RestController
@RequestMapping(value = "/common")
public class CommonController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired CommonService commonService;
	@Autowired TCustomerImagesService customerImagesService;
	/**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${img.location}")
    private String location;
    
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
        try {
            file_name = CommonUtils.saveImg(multipartFile, filePath);
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
	 * 用户来源
	 */
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryRootIn",method=RequestMethod.POST)
	public List<Map<String,Object>> queryRootIn(TCompany bean) {
		try {
			return commonService.queryRootIn(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
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
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
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
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
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
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
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
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryProjectInventory",method=RequestMethod.POST)
	public List<Map<String,Object>> queryProjectInventory(TCompany bean) {
		try {
			return commonService.queryProjectInventory(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/getView",method=RequestMethod.POST)
	public List<Map<String,Object>> getView(TCompany bean) {
		try {
			return commonService.getView(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryEmployeeTreatOrderReport",method=RequestMethod.POST)
	public Map<String, Object> queryEmployeeTreatOrderReport(Integer type,Integer companyid,String datetype,String starttime,String endtime) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊报表数据！");
		try {
			Map<String, Object> map = commonService.queryEmployeeTreatOrderReport(type,companyid,datetype,starttime,endtime);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊报表数据结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryEmployeeTreatOrderDataDetail",method=RequestMethod.POST)
	public Map<String, Object> queryEmployeeTreatOrderDataDetail(Integer type,Integer companyid,String datetype,String starttime,String endtime,Integer page,Integer rows) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊数据列表！");
    	try {
			Map<String, Object> map = commonService.queryEmployeeTreatOrderDataDetail(type,companyid,datetype,starttime,endtime,page,rows);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊数据列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
    	}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test },logical=Logical.OR)
    @RequestMapping(value = "/queryBackEmployeeOrderReport",method=RequestMethod.POST)
	public Map<String, Object> queryBackEmployeeOrderReport(Integer type,Integer companyid,String datetype,String starttime,String endtime) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单报表数据！");
    	try {
			Map<String, Object> map = commonService.queryBackEmployeeOrderReport(type,companyid,datetype,starttime,endtime);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单接诊数据列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test },logical=Logical.OR)
    @RequestMapping(value = "/queryBackEmployeeOrderDataDetail",method=RequestMethod.POST)
	public Map<String, Object> queryBackEmployeeOrderDataDetail(Integer type,Integer companyid,String datetype,String starttime,String endtime,Integer page,Integer rows) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单数据列表！");
    	try {
			Map<String, Object> map = commonService.queryBackEmployeeOrderDataDetail(type,companyid,datetype,starttime,endtime,page,rows);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计员工订单数据列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.QUERY,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryCountInventory",method=RequestMethod.POST)
	public Map<String, Object> queryCountInventory(Integer companyid,String datetype,Integer year,Integer month,Integer page,Integer rows) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计库存数据！");
		try {
			Map<String, Object> map = commonService.queryCountInventory(companyid,datetype,year,month,page,rows);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计库存数据结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.QUERY,RoleSign.Q_OPTION },logical=Logical.OR)
    @RequestMapping(value = "/queryCountInventoryPie",method=RequestMethod.POST)
	public Map<String, Object> queryCountInventoryPie(Integer companyid,String datetype,Integer year,Integer month) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计库存饼图！");
		try {
			Map<String, Object> map = commonService.queryCountInventoryPie(companyid,datetype,year,month);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计库存饼图结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.QUERY },logical=Logical.OR)
    @RequestMapping(value = "/queryCountBusinessAnalysisDataDetail",method=RequestMethod.POST)
	public Map<String, Object> queryCountBusinessAnalysisDataDetail(Integer type,Integer companyid,String datetype,String starttime,String endtime,Integer page,Integer rows) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计商业报表列表！");
    	try {
			Map<String, Object> map = commonService.queryCountBusinessAnalysisDataDetail(type, companyid, datetype, starttime, endtime, page, rows);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计商业报表列表结束！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
}