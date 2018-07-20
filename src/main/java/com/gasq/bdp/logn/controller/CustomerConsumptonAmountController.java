package com.gasq.bdp.logn.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
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
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.service.CustomerConsumptonAmountService;
import com.gasq.bdp.logn.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/ccac")
@Api("用户消费金额API")
public class CustomerConsumptonAmountController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired CustomerConsumptonAmountService customerConsumptonAmountService;
    @Autowired CustomerService customerService;
    @Value("${wf.serverUrlPrefix}")
	private String wfServerUrlPrefix;
    @Autowired
	private RestTemplate restTemplate;
    //错误信
    Map<String,Object> map = new HashMap<String,Object>();
    
    @ApiOperation(value="查询列表用户消费金额信息列表", notes="查询列表用户消费金额信息列表")
    @ApiImplicitParam(name = "bean", value = "sql实体对象TLtnCustomer", required = false, dataType = "TLtnCustomer")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryCustomerList",method=RequestMethod.POST)
	public Map<String, Object> queryCustomerList(TLtnCustomer bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表用户消费订单信息列表！");
		try {
			map = customerService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表用户消费订单信息列表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
    @ApiOperation(value="查询列表用户消费金额信息列表", notes="查询列表用户消费金额信息列表")
    @ApiImplicitParam(name = "bean", value = "sql实体对象TLtnCustomerConsumptonAmount", required = false, dataType = "TLtnCustomerConsumptonAmount")
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test,RoleSign.Q_OPTION },logical=Logical.OR)
	@RequestMapping(value = "/queryCustomerAmountList",method=RequestMethod.POST)
	public Map<String, Object> queryCustomerAmountList(TLtnCustomerConsumptonAmount bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表用户消费金额信息列表！");
		try {
			map = customerConsumptonAmountService.queryPagingList(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询列表用户消费金额信息列表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdateCust",method=RequestMethod.POST)
	public Map<String, Object> saveOrUpdateCust(TLtnCustomer bean) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求新增或修改用户消费金额信息列表！");
		try {
			map = customerService.saveOrUpdate(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求新增或修改用户消费金额信息列表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
	/**
	 * 接诊
	 */
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Q_RECEPTIONIST,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdateTFMCust",method=RequestMethod.POST)
	public boolean saveOrUpdateTFMCust(TLtnCustomer bean) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求开始执行接诊！");
		try {
			Boolean b = customerService.saveOrUpdateTFMCust(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求开始执行接诊结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return b;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
    
//	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/saveOrUpdateCustAmount",method=RequestMethod.POST)
	public Integer saveOrUpdateCustAmount(TLtnCustomerConsumptonAmount bean) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求保存或修改客户消费金额！");
		try {
			Integer i = customerConsumptonAmountService.saveOrUpdate(bean).getId();
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求保存或修改客户消费金额结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return i;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	 }
    
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER},logical=Logical.OR)
	@RequestMapping(value = "/deleteCust",method=RequestMethod.POST)
	public boolean deleteCust(Integer id) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除改客户消费金额！");
		try {
			Boolean f = customerService.delete(id);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除改客户消费金额结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER},logical=Logical.OR)
	@RequestMapping(value = "/refundCust",method=RequestMethod.POST)
	public boolean refundCust(Integer id) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求退款客户消费订单！");
		try {
			Boolean f = customerService.refundCust(id);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求退款客户消费订单结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	
//	@RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION},logical=Logical.OR)
	@RequestMapping(value = "/deleteCustAmount",method=RequestMethod.POST)
	public boolean deleteCustAmount(Integer id) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除客户消费订单！");
		try {
			Boolean f =  customerConsumptonAmountService.delete(id);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求删除客户消费订单结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return f;
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}		return false;
	 }
	
	/**
	 * return total_amount/one_total_amount
	 */
    @ApiOperation(value="统计用户消费金额", notes="统计用户消费金额")
    @ApiImplicitParam(name = "bean", value = "sql实体对象queryAmountSum", required = false, dataType = "queryAmountSum")
//    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/queryAmountSum",method=RequestMethod.POST)
	public Map<String, Object> queryAmountSum(TLtnCustomer bean) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求统计用户消费金额！");
		try {
			map = customerService.queryAmountSum(bean);
			if(map==null) {
				map = new HashMap<String,Object>();
				map.put("total_amount", 0);
			}
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求统计用户消费金额结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return map;
	 }
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/commit",method=RequestMethod.POST)
	public Map<String, Object> commit(TLtnCustomer customer) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求提交用户消费订单！");
		try {
			logger.info("开始请求结算...");
			Map<String, Object> map = customerService.queryPagingList(customer);
			List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("rows");
			if(list!=null && list.size()>0) {
				final String url = wfServerUrlPrefix+"/consumAmount";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
				MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
				//也支持中文
				params.add("phonenumb",list.get(0).get("phonenumb").toString());
				params.add("totalAmount",list.get(0).get("totalConsumptonAmount").toString());
				HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
				//  执行HTTP请求
				JSONObject response = restTemplate.postForObject(url,requestEntity,JSONObject.class);
				if(response.get("isok").toString().equals("0")) {//成功
					customer.setStatus(1);
					customer.setType(2);
					customerService.saveOrUpdate(customer);
					map.put("status", true);
				}else {//失败
					map.put("status", false);
					logger.info("请求结算失败，失败原因" + response.get("mess").toString());
					throw new Exception(response.get("mess").toString());
				}
			}
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求提交用户消费订单结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			map.put("status", false);
			logger.info("请求结算失败，失败原因" + e.getMessage().toString(),e);
		}
    	return map;
	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
   	@RequestMapping(value = "/ptcommit",method=RequestMethod.POST)
   	public Map<String, Object> ptcommit(TLtnCustomer customer) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求普通结算用户消费订单！");
   		try {
   			logger.info("开始进行普通结算...");
   			customer.setStatus(1);
   			customer.setType(1);
   			map = customerService.saveOrUpdate(customer);
   			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求普通结算用户消费订单结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
   		}catch (Exception e) {
   			map.put("status", false);
   			logger.info("结算失败，失败原因" + e.getMessage().toString(),e);
   		}
       	return map;
   	 }
	
//    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.H_ADMIN,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.H_OPTION,RoleSign.QUERY,RoleSign.Test},logical=Logical.OR)
	@RequestMapping(value = "/countConsumptionReport",method=RequestMethod.POST)
	public Map<String, Object> countConsumptionReport(Integer companyid,String datetype,String starttime,String endtime) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计就诊报表！");
		try {
			map = customerService.countConsumptionReport(companyid,datetype,starttime,endtime);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计就诊报表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
	@RequestMapping(value = "/queryCountConsumptionReportList",method=RequestMethod.POST)
	public Map<String, Object> queryCountConsumptionReportList(Integer companyid,String datetype,String starttime,String endtime) {
		Instant start = Instant.now();
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计就诊报表列表！");
		try {
			map = customerService.queryCountConsumptionReportList(companyid,datetype,starttime,endtime);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求查询统计就诊报表列表结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
	
	
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER,RoleSign.GENERALMANAGER,RoleSign.Q_RECEPTIONIST,RoleSign.Q_COUNELOR,RoleSign.Test,RoleSign.Q_OPTION},logical=Logical.OR)
   	@RequestMapping(value = "/hycommit",method=RequestMethod.POST)
   	public Map<String, Object> hycommit(TLtnCustomer customer) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求提交客户消费订单！");
   		try {
   			logger.info("开始进行会员结算...");
   			customer.setStatus(1);
   			customer.setType(3);
   			map = customerService.saveOrUpdate(customer);
   			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求提交客户消费订单结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
   		}catch (Exception e) {
   			map.put("status", false);
   			logger.info("结算失败，失败原因" + e.getMessage().toString(),e);
   		}
       	return map;
   	 }
    
    @RequiresRoles(value={RoleSign.SADMIN,RoleSign.Q_ADMIN,RoleSign.Q_AREA_SHOPMANAGER},logical=Logical.OR)
   	@RequestMapping(value = "/orderBack",method=RequestMethod.POST)
   	public Map<String, Object> orderBack(TLtnCustomer customer) {
    	Instant start = Instant.now();
    	logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求订单回退！");
   		try {
   			logger.info("开始进行订单回退操作...");
   			map = customerService.orderBack(customer);
   			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】请求订单回退结束！总用时："+Duration.between(start, Instant.now()).toMinutes()+"分钟！");
			return map;
   		}catch (Exception e) {
   			map.put("status", false);
   			logger.info("订单回退操作失败，失败原因" + e.getMessage().toString(),e);
   		}
       	return map;
   	 }
    
}

	
	
