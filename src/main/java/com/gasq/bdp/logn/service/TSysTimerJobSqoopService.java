package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerJobSqoop;
import com.gasq.bdp.logn.model.TSysTimerJobSqoopExample;
import java.util.List;
import java.util.Map;

public interface TSysTimerJobSqoopService {
    long countByExample(TSysTimerJobSqoopExample example);

    List<TSysTimerJobSqoop> selectByExample(TSysTimerJobSqoopExample example);

    TSysTimerJobSqoop selectByPrimaryKey(Integer id);

	Map<String, Object> queryPagingList(TSysTimerJobSqoop bean);

	boolean saveOrUpdate(TSysTimerJobSqoop bean);

	boolean delete(int id);
}