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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.gasq.bdp.logn.mapper.TSysUserExtMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExt;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.TCustomerImagesService;
import com.gasq.bdp.logn.utils.CommonUtils;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/common")
@Api(value="公共controller",tags={"公共页面管理"})
public class CommonController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired CommonService commonService;
	@Autowired TCustomerImagesService customerImagesService;
	@Autowired TSysUserExtMapper sysUserExtMapper;
	
	@Value("${uipf.rest.serverUrlPrefix}")
	private String wfServerUrlPrefix;
	@Autowired
	private RestTemplate restTemplate;
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
    
    @Ilogger(value="远程调用")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/commit",method=RequestMethod.POST)
	public Map<String, Object> commit() {
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求远程****接口！");
    	Map<String,Object> map = new HashMap<>();
		try {
			logger.info("开始请求结算...");
				final String url = wfServerUrlPrefix+"/consumAmount";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
				MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
				//也支持中文
				params.add("phonenumb","");
				HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
				//  执行HTTP请求
				JSONObject response = restTemplate.postForObject(url,requestEntity,JSONObject.class);
				if(response.get("isok").toString().equals("0")) {//成功
					map.put("status", true);
				}else {//失败
					map.put("status", false);
					logger.error("请求失败，失败原因" + response.get("mess").toString());
					throw new Exception(response.get("mess").toString());
				}
			return map;
		}catch (Exception e) {
			map.put("status", false);
			logger.error("请求失败，失败原因" + e.getMessage().toString(),e);
		}
    	return map;
	 }
}