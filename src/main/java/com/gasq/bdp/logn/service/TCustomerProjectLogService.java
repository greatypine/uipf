package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;
import com.gasq.bdp.logn.model.TCustomerProjectLog;
import com.gasq.bdp.logn.model.TCustomerProjectLogExample;

public interface TCustomerProjectLogService {
	
	long countByExample(TCustomerProjectLogExample example);

    List<TCustomerProjectLog> selectByExample(TCustomerProjectLog bean);

	Map<String, Object> queryPagingList(TCustomerProjectLog bean);

	boolean saveOrUpdate(TCustomerProjectLog bean);

	boolean delete(int id);

	TCustomerProjectLog selectByPrimaryKey(Integer id);
}