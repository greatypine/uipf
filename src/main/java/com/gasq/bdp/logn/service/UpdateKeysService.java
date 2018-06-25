package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeysExample;

import java.util.List;
import java.util.Map;

public interface UpdateKeysService {

	Map<String, Object> queryPagingList(TSysSqlUpdateKeys bean);

	boolean saveOrUpdate(TSysSqlUpdateKeys bean);

	boolean delete(int id);

	List<TSysSqlUpdateKeys> selectByExample(TSysSqlUpdateKeysExample sqlqueryExample);
	
	long countByExample(TSysSqlUpdateKeysExample example);

	List<TSysSqlUpdateKeys> queryBeanList(TSysSqlUpdateKeys bean);
}