package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobSqoop;
import com.gasq.bdp.logn.model.TSysTimerJobSqoopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobSqoopMapper {
    long countByExample(TSysTimerJobSqoopExample example);

    int deleteByExample(TSysTimerJobSqoopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobSqoop record);

    int insertSelective(TSysTimerJobSqoop record);

    List<TSysTimerJobSqoop> selectByExampleWithRowbounds(TSysTimerJobSqoopExample example, RowBounds rowBounds);

    List<TSysTimerJobSqoop> selectByExample(TSysTimerJobSqoopExample example);

    TSysTimerJobSqoop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobSqoop record, @Param("example") TSysTimerJobSqoopExample example);

    int updateByExample(@Param("record") TSysTimerJobSqoop record, @Param("example") TSysTimerJobSqoopExample example);

    int updateByPrimaryKeySelective(TSysTimerJobSqoop record);

    int updateByPrimaryKey(TSysTimerJobSqoop record);
}