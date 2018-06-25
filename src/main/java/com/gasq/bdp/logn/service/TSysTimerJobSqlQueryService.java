package com.gasq.bdp.logn.service;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQueryExample;
import java.util.List;
import java.util.Map;

public interface TSysTimerJobSqlQueryService {

	Map<String, Object> queryPagingList(TSysTimerJobSqlQuery bean);

	boolean saveOrUpdate(TSysTimerJobSqlQuery bean);

	boolean delete(int id);

	List<TSysTimerJobSqlQuery> selectByExample(TSysTimerJobSqlQueryExample sqlqueryExample);
	
	long countByExample(TSysTimerJobSqlQueryExample example);
}