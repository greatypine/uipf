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
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExt;
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
}