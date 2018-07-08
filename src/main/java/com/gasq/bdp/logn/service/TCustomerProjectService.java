/**
 * 
 */
package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.TCustomerPorject;
import com.gasq.bdp.logn.model.TCustomerPorjectExample;

/**
 * @author Ju_weigang
 * @时间 2018年7月8日上午1:05:53
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public interface TCustomerProjectService {

	Map<String, Object> queryPagingList(TCustomerPorject bean);

	boolean saveOrUpdate(TCustomerPorject bean) throws WorkFlowStateException;

	boolean delete(Integer id);
	
	int deleteByExample(TCustomerPorjectExample example);

	List<TCustomerPorject> selectByExample(TCustomerPorjectExample example);

}
