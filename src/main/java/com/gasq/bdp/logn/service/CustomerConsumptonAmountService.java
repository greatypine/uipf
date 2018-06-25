/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmountExample;

/**
 * @author Ju_weigang
 * @时间 2018年1月22日下午3:52:21
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface CustomerConsumptonAmountService {
	
	 	long countByExample(TLtnCustomerConsumptonAmountExample example);

	    int deleteByExample(TLtnCustomerConsumptonAmountExample example);

	    int insertSelective(TLtnCustomerConsumptonAmount record);

	    List<TLtnCustomerConsumptonAmount> selectByExample(TLtnCustomerConsumptonAmountExample example);

	    TLtnCustomerConsumptonAmount selectByPrimaryKey(Integer id);

		Map<String, Object> queryPagingList(TLtnCustomerConsumptonAmount bean);

		boolean delete(int id);

		TLtnCustomerConsumptonAmount saveOrUpdate(TLtnCustomerConsumptonAmount bean);
}
