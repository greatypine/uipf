package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysDataColumns;
import com.gasq.bdp.logn.model.TSysDataColumnsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysDataColumnsMapper {
    long countByExample(TSysDataColumnsExample example);

    int deleteByExample(TSysDataColumnsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysDataColumns record);

    int insertSelective(TSysDataColumns record);

    List<TSysDataColumns> selectByExampleWithRowbounds(TSysDataColumnsExample example, RowBounds rowBounds);

    List<TSysDataColumns> selectByExample(TSysDataColumnsExample example);

    TSysDataColumns selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysDataColumns record, @Param("example") TSysDataColumnsExample example);

    int updateByExample(@Param("record") TSysDataColumns record, @Param("example") TSysDataColumnsExample example);

    int updateByPrimaryKeySelective(TSysDataColumns record);

    int updateByPrimaryKey(TSysDataColumns record);

	List<Map<String, Object>> getCompareSymbol();

	List<Map<String, Object>> queryDataColumnsTree(Map<String, Object> result);
}