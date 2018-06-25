package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdate;
import com.gasq.bdp.logn.model.TSysTimerJobSqlInsertUpdateExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobSqlInsertUpdateMapper {
    long countByExample(TSysTimerJobSqlInsertUpdateExample example);

    int deleteByExample(TSysTimerJobSqlInsertUpdateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerJobSqlInsertUpdate record);

    int insertSelective(TSysTimerJobSqlInsertUpdate record);

    List<TSysTimerJobSqlInsertUpdate> selectByExampleWithRowbounds(TSysTimerJobSqlInsertUpdateExample example, RowBounds rowBounds);

    List<TSysTimerJobSqlInsertUpdate> selectByExample(TSysTimerJobSqlInsertUpdateExample example);

    TSysTimerJobSqlInsertUpdate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysTimerJobSqlInsertUpdate record, @Param("example") TSysTimerJobSqlInsertUpdateExample example);

    int updateByExample(@Param("record") TSysTimerJobSqlInsertUpdate record, @Param("example") TSysTimerJobSqlInsertUpdateExample example);

    int updateByPrimaryKeySelective(TSysTimerJobSqlInsertUpdate record);

    int updateByPrimaryKey(TSysTimerJobSqlInsertUpdate record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer getMaxId();
}