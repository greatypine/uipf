package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerSwitch;
import com.gasq.bdp.logn.model.TSysTimerSwitchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerSwitchMapper {
    long countByExample(TSysTimerSwitchExample example);

    int deleteByExample(TSysTimerSwitchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerSwitch record);

    int insertSelective(TSysTimerSwitch record);

    List<TSysTimerSwitch> selectByExampleWithRowbounds(TSysTimerSwitchExample example, RowBounds rowBounds);

    List<TSysTimerSwitch> selectByExample(TSysTimerSwitchExample example);

    TSysTimerSwitch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerSwitch record, @Param("example") TSysTimerSwitchExample example);

    int updateByExample(@Param("record") TSysTimerSwitch record, @Param("example") TSysTimerSwitchExample example);

    int updateByPrimaryKeySelective(TSysTimerSwitch record);

    int updateByPrimaryKey(TSysTimerSwitch record);
}