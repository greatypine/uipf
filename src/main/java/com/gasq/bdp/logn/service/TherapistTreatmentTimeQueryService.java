/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TTherapistTreatmentTimeQuery;
import com.gasq.bdp.logn.model.WorkForceParams;

/**
 * @author Ju_weigang
 * @时间 2018年7月30日下午2:13:22
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface TherapistTreatmentTimeQueryService {

	boolean saveOrUpdate(List<TTherapistTreatmentTimeQuery> bean);

	boolean delete(TTherapistTreatmentTimeQuery bean);

	Map<String, Object> queryPagingList(TTherapistTreatmentTimeQuery bean);

	boolean deleteByExample(TTherapistTreatmentTimeQuery bean);

	Map<String, Object> addwf(WorkForceParams bean);

}
