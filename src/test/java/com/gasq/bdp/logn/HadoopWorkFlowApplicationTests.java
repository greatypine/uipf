//package com.gasq.bdp.logn;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.RowBounds;
//import org.apache.log4j.Logger;
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
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
//import com.gasq.bdp.logn.mapper.FSkuclassMapper;
//import com.gasq.bdp.logn.mapper.TCustomerConsumptonAmountMapper;
//import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
//import com.gasq.bdp.logn.model.FSkuclass;
//import com.gasq.bdp.logn.model.FSkuclassExample;
//import com.gasq.bdp.logn.model.SystemUserInfo;
//import com.gasq.bdp.logn.model.TCustomerConsumptonAmount;
//import com.gasq.bdp.logn.model.TLtnCustomer;
//import com.gasq.bdp.logn.model.TLtnCustomerExample;
//import com.gasq.bdp.logn.service.TSysTimerScheduleconfigService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class HadoopWorkFlowApplicationTests {
//	protected Logger logger = Logger.getLogger(this.getClass());
////	@Autowired TSysTimerScheduleconfigService timerScheduleconfigService;
////	@Autowired TSysTimerWorkflowService TSysTimerWorkflow;
////	@Autowired TSysTimerJobconfigService timerJobconfigService;
//	@Value("${wf.serverUrlPrefix}")
//	private String wfServerUrlPrefix;
//    @Autowired
//	private RestTemplate restTemplate;
//    @Autowired TLtnCustomerMapper customerMapper;
//    
//    @Autowired
//    private SolrClient client;
//    @Autowired
//    FSkuclassMapper skuMapper;
////	@Test
////	public void readES() throws Exception {
////		timerScheduleconfigService.manualExecute(3);
////	}
////	@Test
////	public void testrestinterface() {
////		final String url = wfServerUrlPrefix+"/settlement";
////		HttpHeaders headers = new HttpHeaders();
////		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
////		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
////		//也支持中文
////		params.add("username","15652310696");
////		params.add("bugcode","123435");
////		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
////		//  执行HTTP请求
////		JSONObject response = restTemplate.postForObject(url,requestEntity,JSONObject.class);
////		if(response.get("rs").toString().equals("ok")) {//成功
////			
////		}else {//失败
////		}
////	}
//    
////	@Test
////	public void testLimit1() throws Exception {
////		TLtnCustomer bean = new TLtnCustomer();
////		bean.setCompanyId(2);
////		PageHelper.startPage(1, 10);
////		List<Map<String,Object>> list = customerMapper.queryPagingListExt(bean);
//////		TLtnCustomerExample example = new TLtnCustomerExample();
//////		List<TLtnCustomer> list = customerMapper.selectByExample(example);
////		PageInfo<Map<String,Object>> page = new PageInfo<>(list);
////		page.getPageSize();
////	}
//	@Test
//	public void testSolrAdd() throws Exception {
//		FSkuclassExample example = new FSkuclassExample();
//		List<FSkuclass> list = skuMapper.selectByExample(example);
//		for (FSkuclass fSkuclass : list) {
//			SolrInputDocument doc = new SolrInputDocument();
//			doc.setField("sku_id", fSkuclass.getId());
//			doc.setField("sku_name", fSkuclass.getId());
//			doc.setField("sku_content_name", fSkuclass.getId());
//			doc.setField("sku_eshop_id", fSkuclass.getId());
//			doc.setField("sku_role_name", fSkuclass.getId());
//			doc.setField("sku_tag_level1_name", fSkuclass.getId());
//			doc.setField("sku_tag_level1_id", fSkuclass.getId());
//			doc.setField("sku_tag_level2_name", fSkuclass.getId());
//			doc.setField("sku_tag_level2_id", fSkuclass.getId());
//			doc.setField("sku_tag_level3_name", fSkuclass.getId());
//			doc.setField("sku_tag_level3_id", fSkuclass.getId());
//			doc.setField("sku_tag_level4_name", fSkuclass.getId());
//			doc.setField("sku_tag_level4_id", fSkuclass.getId());
//			client.add("core1", doc);
//		}
//        client.commit("core1");
//	}
//	@Test
//	public void testSolrQuery() throws Exception {
////		Instant start = Instant.now();
////		FSkuclassExample example = new FSkuclassExample();
////		List<FSkuclass> list = skuMapper.selectByExample(example);
////		logger.info("请求mysql查询sku数据列表结束。查询总条数"+list.size()+"！总用时："+Duration.between(start, Instant.now()).getSeconds()+"秒！");
//		Instant start1 = Instant.now();
//		SolrQuery query = new SolrQuery();
//		query.setQuery("*:*");
//		query.addField("*");
//		//设置高亮显示
//		query.setHighlight(true);
//		query.addHighlightField("sku_name");
//		query.setHighlightSimplePre("<em style=\"color:red\">");
//		query.setHighlightSimplePost("</em>");
//		QueryResponse queryResponse = client.query("core1", query);
//		SolrDocumentList results = queryResponse.getResults();
////		queryResponse.getBeans(type)
//		logger.info("请求solr查询sku数据列表结束。查询总条数"+results.getNumFound()+"！总用时："+Duration.between(start1, Instant.now()).getSeconds()+"秒！");
//	}
//}
