package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerLog;
import com.gasq.bdp.logn.model.TSysTimerLogExample;
import java.util.List;
import java.util.Map;

public interface TSysTimerLogService {

	Map<String, Object> queryPagingList(TSysTimerLog bean);

	boolean saveOrUpdate(TSysTimerLog bean);

	boolean delete(int id);

	List<TSysTimerLog> selectByExample(TSysTimerLogExample sqlqueryExample);
	
	long countByExample(TSysTimerLogExample example);
	
	Integer getMaxNewId();

	void add(TSysTimerLog tSysTimerLog);
}