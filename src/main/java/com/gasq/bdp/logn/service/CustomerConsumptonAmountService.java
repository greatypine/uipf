/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmountExample;

/**
 * @author Ju_weigang
 * @时间 2018年1月22日下午3:52:21
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface CustomerConsumptonAmountService {
	
	 	long countByExample(TCustomerConsumptonAmountExample example);

	    int deleteByExample(TCustomerConsumptonAmountExample example);

	    int insertSelective(TCustomerConsumptonAmount record);

	    List<TCustomerConsumptonAmount> selectByExample(TCustomerConsumptonAmountExample example);

	    TCustomerConsumptonAmount selectByPrimaryKey(Integer id);

		Map<String, Object> queryPagingList(TCustomerConsumptonAmount bean);

		boolean delete(int id);

		TCustomerConsumptonAmount saveOrUpdate(TCustomerConsumptonAmount bean);
}
