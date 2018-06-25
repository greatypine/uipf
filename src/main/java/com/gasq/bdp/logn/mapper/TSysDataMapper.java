package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysData;
import com.gasq.bdp.logn.model.TSysDataExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysDataMapper {
    long countByExample(TSysDataExample example);

    int deleteByExample(TSysDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysData record);

    int insertSelective(TSysData record);

    List<TSysData> selectByExampleWithRowbounds(TSysDataExample example, RowBounds rowBounds);

    List<TSysData> selectByExample(TSysDataExample example);

    TSysData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysData record, @Param("example") TSysDataExample example);

    int updateByExample(@Param("record") TSysData record, @Param("example") TSysDataExample example);

    int updateByPrimaryKeySelective(TSysData record);

    int updateByPrimaryKey(TSysData record);

	List<Map<String, Object>> queryDataTree(@Param("record") TSysData bean);

	List<Map<String, Object>> getDateType();
}