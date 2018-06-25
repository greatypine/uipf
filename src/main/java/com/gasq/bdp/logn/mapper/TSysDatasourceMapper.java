package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysDatasource;
import com.gasq.bdp.logn.model.TSysDatasourceExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysDatasourceMapper {
    long countByExample(TSysDatasourceExample example);

    int deleteByExample(TSysDatasourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysDatasource record);

    int insertSelective(TSysDatasource record);

    List<TSysDatasource> selectByExampleWithRowbounds(TSysDatasourceExample example, RowBounds rowBounds);

    List<TSysDatasource> selectByExample(TSysDatasourceExample example);

    TSysDatasource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysDatasource record, @Param("example") TSysDatasourceExample example);

    int updateByExample(@Param("record") TSysDatasource record, @Param("example") TSysDatasourceExample example);

    int updateByPrimaryKeySelective(TSysDatasource record);

    int updateByPrimaryKey(TSysDatasource record);

	List<Map<String, Object>> queryDataBaseTree(@Param("record") TSysDatasource bean);
}