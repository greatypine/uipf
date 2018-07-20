package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.Map;
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
import com.gasq.bdp.logn.model.TOrder;
import com.gasq.bdp.logn.model.TScoreExchange;
import com.gasq.bdp.logn.model.TUser;
import com.gasq.bdp.logn.service.TScoreExchangeService;
import com.gasq.bdp.logn.utils.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/score_exchange")
@Api("积分兑换配置接口API")
public class ScoreExchangeController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TScoreExchangeService exchangeService;
    @Value("${wf.serverUrlPrefix}")
	private String wfServerUrlPrefix;
    @Autowired
	private RestTemplate restTemplate;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表积分兑换配置信息列表", notes="查询列表积分兑换配置信息列表")
    @ApiImplicitParam(name = "bean", value = "积分兑换实体对象TScoreExchange", required = false, dataType = "TScoreExchange")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TScoreExchange bean) {
		try {
			return exchangeService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="删除积分兑换配置信息", notes="删除积分兑换配置信息（管理员、操作）")
    @ApiImplicitParam(name = "id", value = "积分兑换实体id", required = true, dataType = "Integer", paramType="query")
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public boolean delete(Integer id) {
		try {
			return exchangeService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public JSONObject saveOrUpdate(String username,String buycode) {
		JSONObject response = null;
		try {
			final String url = wfServerUrlPrefix+"/settlement";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
			//也支持中文
			params.add("username",username);
			params.add("buycode",buycode);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
			//  执行HTTP请求
			response = restTemplate.postForObject(url,requestEntity,JSONObject.class);
			if(response.get("rs").toString().equals("ok")) {//成功
				Object userobj = response.get("user");
				TUser user = CommonUtils.json2Bean(CommonUtils.bean2Json(userobj), TUser.class);
				Object orderobj = response.get("order");
				TOrder order = CommonUtils.json2Bean(CommonUtils.bean2Json(orderobj), TOrder.class);
				TScoreExchange bean = new TScoreExchange();
 				bean.setUser(user);
				bean.setOrder(order);
				boolean b = exchangeService.saveOrUpdate(bean);
				if(b) {
					response.put("status", true);
					return response;
				}else {
					response.put("status", false);
					response.put("rs", "保存兑现信息失败！");
					return response;
				}
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

	
	
