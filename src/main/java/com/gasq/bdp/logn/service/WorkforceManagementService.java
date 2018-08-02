/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TWorkforcemanagement;
import com.gasq.bdp.logn.model.WorkForceParams;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午2:13:22
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface WorkforceManagementService {

	boolean saveOrUpdate(List<TWorkforcemanagement> bean);

	boolean delete(TWorkforcemanagement bean);

	Map<String, Object> queryPagingList(TWorkforcemanagement bean);

	boolean deleteByExample(TWorkforcemanagement bean);

	Map<String, Object> addwf(WorkForceParams bean);

	List<Map<String, Object>> queryExportDataList(TWorkforcemanagement bean);

}
