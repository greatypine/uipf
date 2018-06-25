package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobElasticsearch;
import com.gasq.bdp.logn.model.TSysTimerJobElasticsearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobElasticsearchMapper {
    long countByExample(TSysTimerJobElasticsearchExample example);

    int deleteByExample(TSysTimerJobElasticsearchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobElasticsearch record);

    int insertSelective(TSysTimerJobElasticsearch record);

    List<TSysTimerJobElasticsearch> selectByExampleWithRowbounds(TSysTimerJobElasticsearchExample example, RowBounds rowBounds);

    List<TSysTimerJobElasticsearch> selectByExample(TSysTimerJobElasticsearchExample example);

    TSysTimerJobElasticsearch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobElasticsearch record, @Param("example") TSysTimerJobElasticsearchExample example);

    int updateByExample(@Param("record") TSysTimerJobElasticsearch record, @Param("example") TSysTimerJobElasticsearchExample example);

    int updateByPrimaryKeySelective(TSysTimerJobElasticsearch record);

    int updateByPrimaryKey(TSysTimerJobElasticsearch record);
}