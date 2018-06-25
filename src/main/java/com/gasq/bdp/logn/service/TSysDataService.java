package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.TSysData;
import java.util.List;
import java.util.Map;

public interface TSysDataService {

    List<TSysData> queryList(TSysData bean);

    TSysData selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysData bean);

	boolean saveOrUpdate(TSysData bean);

	boolean delete(int id) throws WorkFlowStateException;

	List<Map<String, Object>> queryDataTree(TSysData bean);

	List<Map<String, Object>> getDateType();

	List<Map<String, Object>> queryDataColumnsTree(TSysData bean);
	
}