package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumnsExample;

import java.util.List;
import java.util.Map;

public interface UpdateColumnsService {

	Map<String, Object> queryPagingList(TSysSqlUpdateColumns bean);

	boolean saveOrUpdate(TSysSqlUpdateColumns bean);

	boolean delete(int id);

	List<TSysSqlUpdateColumns> selectByExample(TSysSqlUpdateColumnsExample sqlqueryExample);
	
	long countByExample(TSysSqlUpdateColumnsExample example);

	List<Map<String, Object>> queryList(TSysSqlUpdateColumns bean);

	List<TSysSqlUpdateColumns> queryBeanList(TSysSqlUpdateColumns sqlUpdateColumns);
}