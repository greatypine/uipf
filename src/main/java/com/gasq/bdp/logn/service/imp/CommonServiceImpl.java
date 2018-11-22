/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TCompany;
import com.gasq.bdp.logn.provider.Ilogger;
import com.gasq.bdp.logn.service.CommonService;

/**
 * @author Ju_weigang
 * @时间 2018年6月1日下午12:52:18
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class CommonServiceImpl implements CommonService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired TCompanyMapper companyMapper;
	@Value("${gabdp.rest.getCurrentUserInfo}")
    String user_info_url;
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@Cacheable(value=InitProperties.UIPF_CACHE,key="#root.methodName+'_'+#p0.id+'_'+#p0.pid+'_'+#p0.sortName+'_'+#p0.viewname",unless = "#result == null")
	public List<Map<String, Object>> getView(TCompany bean) {
		return companyMapper.getView(bean);
	}
	
	@Override
	@Ilogger(value="远程调用用户信息")
	public SystemUser getCurrentUserInfo() {
		SystemUser systemuser = null;
    	Subject subject = SecurityUtils.getSubject();
		try {
			logger.info("开始请求结算...");
			final String url = user_info_url+"/"+subject.getPrincipal().toString();
			ResponseEntity<SystemUser> responseEntity = restTemplate.postForEntity(url,subject.getPrincipal().toString(), SystemUser.class);
			systemuser = responseEntity.getBody();
		}catch (Exception e) {
			logger.error("请求失败，失败原因" + e.getMessage().toString(),e);
		}
    	return systemuser;
	 }
}
