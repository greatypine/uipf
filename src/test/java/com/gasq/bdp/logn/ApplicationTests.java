package com.gasq.bdp.logn;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.gasq.bdp.logn.component.ImpalaOptions;
import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TStore;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.utils.CommonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TCompanyMapper companyMapper;
    @Autowired CommonService commonService;
    @Value("${gabdp.rest.getCurrentUserInfo}")
    String user_info_url;
	@Autowired
	private RestTemplate restTemplate;
    @Autowired
    ImpalaOptions impalaOptions;
    
//    String ip = "http://123.56.204.170:9999";
//    static String ip = "http://10.16.21.80:9999";
    static String ip = "https://login-info.guoanshuju.com";
    
	@Test
	public void readES() throws Exception {
		List<Map<String, Object>> queryForList = impalaOptions.queryImpalaList("SELECT * from gabdp_user.t_city");
		queryForList.stream().forEach(f->System.out.println(f.toString()));
	}
	
	@Test
	public void testRestTemplate() throws Exception {
		final String url = user_info_url+"/juwg";
		ResponseEntity<SystemUser> responseEntity = restTemplate.postForEntity(url, "juwg", SystemUser.class);
        System.out.println(responseEntity.getBody());
	}
	@Test
	public void test1RestTemplate() throws Exception {
		final String url = ip+"/systemuser/getCurrentUserInfo";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("username", "testdz");
		params.add("password", "e10adc3949ba59abbe56e057f20f883e");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<SystemUser> responseEntity = restTemplate.postForEntity(url,request, SystemUser.class);
        System.out.println(responseEntity.getBody());
	}
	@Test
	public void testUserSaveOrUpdate() throws Exception {
		String url = ip+"/systemuser/saveOrUpdate";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		TSysUser user = new TSysUser();
		user.setUsername("test");
		user.setAge(110);
		user.setSex(1);
		user.setStatus(99);
		user.setIsvalid(false);
		HttpEntity<String> entity = new HttpEntity<String>(CommonUtils.BeanToJSON(user), headers);
		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, entity, JSONObject.class);
        System.out.println(responseEntity.getBody());
	}
	
	@Test
	public void testUserDelete() throws Exception {
		String url = "http://123.56.204.170:9999/systemuser/delete";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("username", "juwg");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, request, Boolean.class);
        System.out.println(responseEntity.getBody());
	}
	
	
	@Test
	public void testgetStoreInfo() throws Exception {
		final String url = ip+"/store/getStoreInfo";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("username", "testdz1");
		params.add("password", "e10adc3949ba59abbe56e057f20f883e");
		params.add("storeid", "34");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<TStore> responseEntity = restTemplate.postForEntity(url,request, TStore.class);
        System.out.println(responseEntity.getBody());
	}
	@Test
	public void testsaveOrUpdateStore() throws Exception {
		String url = ip+"/store/saveOrUpdate";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		TStore bean = new TStore();
		bean.setStoreId((long)9900620);
		bean.setName("尖山店test1");
		HttpEntity<String> entity = new HttpEntity<String>(CommonUtils.BeanToJSON(bean), headers);
		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, entity, JSONObject.class);
        System.out.println(responseEntity.getBody());
	}
	
	@Test
	public void testStoreDelete() throws Exception {
		String url = ip+"/store/delete";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("username", "testdz1");
		params.add("password", "e10adc3949ba59abbe56e057f20f883e");
		params.add("storeid", "9900620");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, request, Boolean.class);
        System.out.println(responseEntity.getBody());
	}
}
