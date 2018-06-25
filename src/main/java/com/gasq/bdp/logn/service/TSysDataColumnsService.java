package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import java.util.List;
import java.util.Map;

public interface TSysDataColumnsService {

    List<TSysDataColumns> selectByExample(TSysDataColumnsExample example);

    TSysDataColumns selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysDataColumns bean);

	boolean saveOrUpdate(TSysDataColumns bean);

	boolean delete(int id);

	List<Map<String, Object>> getCompareSymbol();

	List<TSysDataColumns> queryList(TSysDataColumns bean);
}