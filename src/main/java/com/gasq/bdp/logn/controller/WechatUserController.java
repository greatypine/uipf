package com.gasq.bdp.logn.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.TUser;
import com.gasq.bdp.logn.utils.CommonUtils;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/wechat_user")
@Api("微信用户配置接口API")
public class WechatUserController {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Value("${wf.serverUrlPrefix}")
	private String wfServerUrlPrefix;
    @Autowired
	private RestTemplate restTemplate;
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryWechatUserInfo",method=RequestMethod.POST)
	public JSONObject queryWechatUserInfo(String phonenumb) {
		JSONObject response = null;
		try {
			final String url = wfServerUrlPrefix+"/queryUserInfo";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
			//也支持中文
			params.add("phonenumb",phonenumb);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
			//  执行HTTP请求
			response = restTemplate.postForObject(url,requestEntity,JSONObject.class);
//			response = restTemplate.getForObject(url,JSONObject.class,requestEntity);
			if(response.get("rs").toString().equals("ok")) {//成功
				Object userobj = response.get("user");
				TUser user = CommonUtils.json2Bean(CommonUtils.bean2Json(userobj), TUser.class);
				response.put("status", true);
				response.put("user", user);
				return response;
			}else {//失败
				response.put("status", false);
				return response;
			}
		} catch (Exception e) {
			response = new JSONObject();
			response.put("status", false);
			logger.info(e.getMessage(),e);
		}
		return response;
	 }
}

	
	
