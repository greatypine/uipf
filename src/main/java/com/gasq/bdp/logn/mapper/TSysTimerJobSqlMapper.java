package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobSql;
import com.gasq.bdp.logn.model.TSysTimerJobSqlExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobSqlMapper {
    long countByExample(TSysTimerJobSqlExample example);

    int deleteByExample(TSysTimerJobSqlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerJobSql record);

    int insertSelective(TSysTimerJobSql record);

    List<TSysTimerJobSql> selectByExampleWithBLOBsWithRowbounds(TSysTimerJobSqlExample example, RowBounds rowBounds);

    List<TSysTimerJobSql> selectByExampleWithBLOBs(TSysTimerJobSqlExample example);

    List<TSysTimerJobSql> selectByExampleWithRowbounds(TSysTimerJobSqlExample example, RowBounds rowBounds);

    List<TSysTimerJobSql> selectByExample(TSysTimerJobSqlExample example);

    TSysTimerJobSql selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysTimerJobSql record, @Param("example") TSysTimerJobSqlExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerJobSql record, @Param("example") TSysTimerJobSqlExample example);

    int updateByExample(@Param("record") TSysTimerJobSql record, @Param("example") TSysTimerJobSqlExample example);

    int updateByPrimaryKeySelective(TSysTimerJobSql record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerJobSql record);

    int updateByPrimaryKey(TSysTimerJobSql record);

	List<Map<String, Object>> querySqlpagingList(Map<String, Object> params);
}