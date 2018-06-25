package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobExeshell;
import com.gasq.bdp.logn.model.TSysTimerJobExeshellExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobExeshellMapper {
    long countByExample(TSysTimerJobExeshellExample example);

    int deleteByExample(TSysTimerJobExeshellExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobExeshell record);

    int insertSelective(TSysTimerJobExeshell record);

    List<TSysTimerJobExeshell> selectByExampleWithBLOBsWithRowbounds(TSysTimerJobExeshellExample example, RowBounds rowBounds);

    List<TSysTimerJobExeshell> selectByExampleWithBLOBs(TSysTimerJobExeshellExample example);

    List<TSysTimerJobExeshell> selectByExampleWithRowbounds(TSysTimerJobExeshellExample example, RowBounds rowBounds);

    List<TSysTimerJobExeshell> selectByExample(TSysTimerJobExeshellExample example);

    TSysTimerJobExeshell selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobExeshell record, @Param("example") TSysTimerJobExeshellExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysTimerJobExeshell record, @Param("example") TSysTimerJobExeshellExample example);

    int updateByExample(@Param("record") TSysTimerJobExeshell record, @Param("example") TSysTimerJobExeshellExample example);

    int updateByPrimaryKeySelective(TSysTimerJobExeshell record);

    int updateByPrimaryKeyWithBLOBs(TSysTimerJobExeshell record);

    int updateByPrimaryKey(TSysTimerJobExeshell record);
}