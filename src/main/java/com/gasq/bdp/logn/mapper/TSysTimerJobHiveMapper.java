package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobHive;
import com.gasq.bdp.logn.model.TSysTimerJobHiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobHiveMapper {
    long countByExample(TSysTimerJobHiveExample example);

    int deleteByExample(TSysTimerJobHiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobHive record);

    int insertSelective(TSysTimerJobHive record);

    List<TSysTimerJobHive> selectByExampleWithBLOBsWithRowbounds(TSysTimerJobHiveExample example, RowBounds rowBounds);

    List<TSysTimerJobHive> selectByExampleWithBLOBs(TSysTimerJobHiveExample example);

    List<TSysTimerJobHive> selectByExampleWithRowbounds(TSysTimerJobHiveExample example, RowBounds rowBounds);

    List<TSysTimerJobHive> selectByExample(TSysTimerJobHiveExample example);

    TSysTimerJobHive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobHive record, @Param("example") TSysTimerJobHiveExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerJobHive record, @Param("example") TSysTimerJobHiveExample example);

    int updateByExample(@Param("record") TSysTimerJobHive record, @Param("example") TSysTimerJobHiveExample example);

    int updateByPrimaryKeySelective(TSysTimerJobHive record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerJobHive record);

    int updateByPrimaryKey(TSysTimerJobHive record);
}