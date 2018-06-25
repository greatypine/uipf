package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysSqlUpdateKeys;
import com.gasq.bdp.logn.model.TSysSqlUpdateKeysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysSqlUpdateKeysMapper {
    long countByExample(TSysSqlUpdateKeysExample example);

    int deleteByExample(TSysSqlUpdateKeysExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysSqlUpdateKeys record);

    int insertSelective(TSysSqlUpdateKeys record);

    List<TSysSqlUpdateKeys> selectByExampleWithRowbounds(TSysSqlUpdateKeysExample example, RowBounds rowBounds);

    List<TSysSqlUpdateKeys> selectByExample(TSysSqlUpdateKeysExample example);

    TSysSqlUpdateKeys selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysSqlUpdateKeys record, @Param("example") TSysSqlUpdateKeysExample example);

    int updateByExample(@Param("record") TSysSqlUpdateKeys record, @Param("example") TSysSqlUpdateKeysExample example);

    int updateByPrimaryKeySelective(TSysSqlUpdateKeys record);

    int updateByPrimaryKey(TSysSqlUpdateKeys record);
}