/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.TCompany;
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
	
	@Override
	@Cacheable(value=InitProperties.UIPF_CACHE,key="#root.methodName+'_'+#p0.id+'_'+#p0.pid+'_'+#p0.sortName+'_'+#p0.viewname",unless = "#result == null")
	public List<Map<String, Object>> getView(TCompany bean) {
		return companyMapper.getView(bean);
	}
}
