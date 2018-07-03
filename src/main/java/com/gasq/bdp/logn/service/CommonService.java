/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TCompany;

/**
 * @author Ju_weigang
 * @时间 2018年6月1日下午12:49:07
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface CommonService {

	List<Map<String, Object>> queryRootIn(TCompany bean);

	List<Map<String, Object>> querySex(TCompany bean);

	List<Map<String, Object>> queryUserStatus(TCompany bean);

	List<Map<String, Object>> queryCosmetologist(TCompany bean);

	List<Map<String, Object>> queryCounsoler(TCompany bean);

	List<Map<String, Object>> queryProjectInventory(TCompany bean);

	List<Map<String, Object>> getView(TCompany bean);

	Map<String, Object> queryEmployeeTreatOrderReport(Integer type, Integer companyid, String datetype,
			String starttime, String endtime);

	Map<String, Object> queryEmployeeTreatOrderDataDetail(Integer type, Integer companyid, String datetype,
			String starttime, String endtime);

}
