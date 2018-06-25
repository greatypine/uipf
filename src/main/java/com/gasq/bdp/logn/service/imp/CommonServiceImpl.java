/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.mapper.TCompanyMapper;
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
	@Autowired TCompanyMapper companyMapper;

	@Override
	public List<Map<String, Object>> queryRootIn(TCompany bean) {
		return companyMapper.queryRootIn(bean);
	}

	@Override
	public List<Map<String, Object>> querySex(TCompany bean) {
		return companyMapper.querySex(bean);
	}

	@Override
	public List<Map<String, Object>> queryUserStatus(TCompany bean) {
		return companyMapper.queryUserStatus(bean);
	}

	@Override
	public List<Map<String, Object>> queryCosmetologist(TCompany bean) {
		return companyMapper.queryCosmetologist(bean);
	}

	@Override
	public List<Map<String, Object>> queryCounsoler(TCompany bean) {
		return companyMapper.queryCounsoler(bean);
	}

	@Override
	public List<Map<String, Object>> queryProjectInventory(TCompany bean) {
		return companyMapper.queryProjectInventory(bean);
	}

	@Override
	public List<Map<String, Object>> getView(TCompany bean) {
		return companyMapper.getView(bean);
	}

}
