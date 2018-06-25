package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobSqlQuery;
import com.gasq.bdp.logn.model.TSysTimerJobSqlQueryExample;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobSqlQueryMapper {
    long countByExample(TSysTimerJobSqlQueryExample example);

    int deleteByExample(TSysTimerJobSqlQueryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerJobSqlQuery record);

    int insertSelective(TSysTimerJobSqlQuery record);

    List<TSysTimerJobSqlQuery> selectByExampleWithBLOBsWithRowbounds(TSysTimerJobSqlQueryExample example, RowBounds rowBounds);

    List<TSysTimerJobSqlQuery> selectByExampleWithBLOBs(TSysTimerJobSqlQueryExample example);

    List<TSysTimerJobSqlQuery> selectByExampleWithRowbounds(TSysTimerJobSqlQueryExample example, RowBounds rowBounds);

    List<TSysTimerJobSqlQuery> selectByExample(TSysTimerJobSqlQueryExample example);

    TSysTimerJobSqlQuery selectByPrimaryKey(Long id)throws SQLException;

    int updateByExampleSelective(@Param("record") TSysTimerJobSqlQuery record, @Param("example") TSysTimerJobSqlQueryExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerJobSqlQuery record, @Param("example") TSysTimerJobSqlQueryExample example);

    int updateByExample(@Param("record") TSysTimerJobSqlQuery record, @Param("example") TSysTimerJobSqlQueryExample example);

    int updateByPrimaryKeySelective(TSysTimerJobSqlQuery record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerJobSqlQuery record);

    int updateByPrimaryKey(TSysTimerJobSqlQuery record);

	List<Map<String, Object>> querySqlpagingList(Map<String, Object> params);
}