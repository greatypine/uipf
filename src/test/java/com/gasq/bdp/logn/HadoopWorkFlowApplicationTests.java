//package com.gasq.bdp.logn;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import com.alibaba.fastjson.JSONObject;
//import com.gasq.bdp.logn.service.TSysTimerJobconfigService;
//import com.gasq.bdp.logn.service.TSysTimerScheduleconfigService;
//import com.gasq.bdp.logn.service.TSysTimerWorkflowService;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class HadoopWorkFlowApplicationTests {
////	@Autowired TSysTimerScheduleconfigService timerScheduleconfigService;
////	@Autowired TSysTimerWorkflowService TSysTimerWorkflow;
////	@Autowired TSysTimerJobconfigService timerJobconfigService;
//	@Value("${wf.serverUrlPrefix}")
//	private String wfServerUrlPrefix;
//    @Autowired
//	private RestTemplate restTemplate;
////	@Test
////	public void readES() throws Exception {
////		timerScheduleconfigService.manualExecute(3);
////	}
//	@Test
//	public void testrestinterface() {
//		final String url = wfServerUrlPrefix+"/settlement";
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
//		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//		//也支持中文
//		params.add("username","15652310696");
//		params.add("bugcode","123435");
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//		//  执行HTTP请求
//		JSONObject response = restTemplate.postForObject(url,requestEntity,JSONObject.class);
//		if(response.get("rs").toString().equals("ok")) {//成功
//			
//		}else {//失败
//		}
//	}
//}
