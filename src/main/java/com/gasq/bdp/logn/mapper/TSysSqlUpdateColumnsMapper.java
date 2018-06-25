package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysSqlUpdateColumns;
import com.gasq.bdp.logn.model.TSysSqlUpdateColumnsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysSqlUpdateColumnsMapper {
    long countByExample(TSysSqlUpdateColumnsExample example);

    int deleteByExample(TSysSqlUpdateColumnsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysSqlUpdateColumns record);

    int insertSelective(TSysSqlUpdateColumns record);

    List<TSysSqlUpdateColumns> selectByExampleWithRowbounds(TSysSqlUpdateColumnsExample example, RowBounds rowBounds);

    List<TSysSqlUpdateColumns> selectByExample(TSysSqlUpdateColumnsExample example);

    TSysSqlUpdateColumns selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysSqlUpdateColumns record, @Param("example") TSysSqlUpdateColumnsExample example);

    int updateByExample(@Param("record") TSysSqlUpdateColumns record, @Param("example") TSysSqlUpdateColumnsExample example);

    int updateByPrimaryKeySelective(TSysSqlUpdateColumns record);

    int updateByPrimaryKey(TSysSqlUpdateColumns record);

	List<Map<String, Object>> queryList(@Param("record") TSysSqlUpdateColumns bean);
}