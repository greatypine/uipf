package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TSysDatasource;
import com.gasq.bdp.logn.model.TSysDatasourceExample;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TSysDatasourceService {

    int add(TSysDatasource record);

    List<TSysDatasource> selectByExample(TSysDatasourceExample example);

    TSysDatasource selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysDatasource bean);

	boolean saveOrUpdate(TSysDatasource bean);

	boolean delete(int id);

	boolean check(TSysDatasource bean) throws WorkFlowJobException, SQLException;

	List<Map<String, Object>> queryDataBaseTree(TSysDatasource bean);

	List<Map<String, Object>> getColumnsByTable(String dbcode, String tablename) throws WorkFlowJobException;

	List<Map<String, Object>> queryTablesByDb(String dbcode) throws WorkFlowJobException;
}