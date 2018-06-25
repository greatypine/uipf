package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerWfExeInfo;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfoExample;
import java.util.List;
import java.util.Map;

public interface TSysTimerWfExeInfoService {
	Map<String, Object> queryPagingList(TSysTimerWfExeInfo bean);

	boolean saveOrUpdate(TSysTimerWfExeInfo bean);

	boolean delete(int id);

	List<TSysTimerWfExeInfo> selectByExample(TSysTimerWfExeInfoExample sqlqueryExample);
	
	long countByExample(TSysTimerWfExeInfoExample example);

	List<TSysTimerWfExeInfo> queryList(TSysTimerWfExeInfo exeInfo);
}