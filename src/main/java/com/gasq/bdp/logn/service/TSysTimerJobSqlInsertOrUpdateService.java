package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdateExample;

import java.util.List;
import java.util.Map;

public interface TSysTimerJobSqlInsertOrUpdateService {

	Map<String, Object> queryPagingList(TSysTimerJobSqlInsertUpdate bean);

	boolean saveOrUpdate(TSysTimerJobSqlInsertUpdate bean);

	boolean delete(int id);

	List<TSysTimerJobSqlInsertUpdate> selectByExample(TSysTimerJobSqlInsertUpdateExample sqlqueryExample);
	
	long countByExample(TSysTimerJobSqlInsertUpdateExample example);

	boolean saveAll(String baseData, String keys, String columns) throws WorkFlowJobException;
}