package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerJobSql;
import com.gasq.bdp.logn.model.TSysTimerJobSqlExample;

import java.util.List;
import java.util.Map;

public interface TSysTimerJobSqlService {

	Map<String, Object> queryPagingList(TSysTimerJobSql bean);

	boolean saveOrUpdate(TSysTimerJobSql bean);

	boolean delete(int id);

	List<TSysTimerJobSql> selectByExample(TSysTimerJobSqlExample sqlqueryExample);
	
	long countByExample(TSysTimerJobSqlExample example);
}