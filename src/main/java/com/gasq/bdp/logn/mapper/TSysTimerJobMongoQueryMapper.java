package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobMongoQuery;
import com.gasq.bdp.logn.model.TSysTimerJobMongoQueryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobMongoQueryMapper {
    long countByExample(TSysTimerJobMongoQueryExample example);

    int deleteByExample(TSysTimerJobMongoQueryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysTimerJobMongoQuery record);

    int insertSelective(TSysTimerJobMongoQuery record);

    List<TSysTimerJobMongoQuery> selectByExampleWithBLOBsWithRowbounds(TSysTimerJobMongoQueryExample example, RowBounds rowBounds);

    List<TSysTimerJobMongoQuery> selectByExampleWithBLOBs(TSysTimerJobMongoQueryExample example);

    List<TSysTimerJobMongoQuery> selectByExampleWithRowbounds(TSysTimerJobMongoQueryExample example, RowBounds rowBounds);

    List<TSysTimerJobMongoQuery> selectByExample(TSysTimerJobMongoQueryExample example);

    TSysTimerJobMongoQuery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysTimerJobMongoQuery record, @Param("example") TSysTimerJobMongoQueryExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerJobMongoQuery record, @Param("example") TSysTimerJobMongoQueryExample example);

    int updateByExample(@Param("record") TSysTimerJobMongoQuery record, @Param("example") TSysTimerJobMongoQueryExample example);

    int updateByPrimaryKeySelective(TSysTimerJobMongoQuery record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerJobMongoQuery record);

    int updateByPrimaryKey(TSysTimerJobMongoQuery record);

	List<Map<String, Object>> querySqlpagingList(Map<String, Object> params);

	Integer getMaxId();
}