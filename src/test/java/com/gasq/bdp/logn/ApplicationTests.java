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
		final String url = "http://123.56.204.170:9999/systemuser/getCurrentUserInfo";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("username", "juwg");
		params.add("password", "21232f297a57a5a743894a0e4a801fc3");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<SystemUser> responseEntity = restTemplate.postForEntity(url,request, SystemUser.class);
        System.out.println(responseEntity.getBody());
	}
	@Test
	public void testUserSaveOrUpdate() throws Exception {
		String url = "http://123.56.204.170:9999/systemuser/saveOrUpdate";
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
}
