/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.gasq.bdp.logn.model.TLtnCustomerExample;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.TLtnCustomer;

/**
 * @author Ju_weigang
 * @时间 2018年1月22日下午3:52:21
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface CustomerService {
	
	 	long countByExample(TLtnCustomerExample example);

	    int deleteByExample(TLtnCustomerExample example);

	    int insertSelective(TLtnCustomer record);

	    List<TLtnCustomer> selectByExample(TLtnCustomerExample example);

	    TLtnCustomer selectByPrimaryKey(Integer id);

		Map<String, Object> queryPagingList(TLtnCustomer bean);

		Map<String, Object> saveOrUpdate(TLtnCustomer bean) throws SchedulerException;

		boolean delete(int id) throws WorkFlowStateException;

		Map<String, Object> queryAmountSum(TLtnCustomer bean);

		List<Map<String, Object>> queryExportDataList(TLtnCustomer customer);

		Map<String, Object> countConsumptionReport(Integer companyid, String datetype, String starttime, String endtime);

		boolean saveOrUpdateTFMCust(TLtnCustomer bean) throws SchedulerException;

		Map<String, Object> queryCountConsumptionReportList(Integer companyid, String datetype, String starttime, String endtime);

		boolean refundCust(int id) throws WorkFlowStateException;
}
