package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysMongoQueryKeys;
import com.gasq.bdp.logn.model.TSysMongoQueryKeysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysMongoQueryKeysMapper {
    long countByExample(TSysMongoQueryKeysExample example);

    int deleteByExample(TSysMongoQueryKeysExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysMongoQueryKeys record);

    int insertSelective(TSysMongoQueryKeys record);

    List<TSysMongoQueryKeys> selectByExampleWithRowbounds(TSysMongoQueryKeysExample example, RowBounds rowBounds);

    List<TSysMongoQueryKeys> selectByExample(TSysMongoQueryKeysExample example);

    TSysMongoQueryKeys selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysMongoQueryKeys record, @Param("example") TSysMongoQueryKeysExample example);

    int updateByExample(@Param("record") TSysMongoQueryKeys record, @Param("example") TSysMongoQueryKeysExample example);

    int updateByPrimaryKeySelective(TSysMongoQueryKeys record);

    int updateByPrimaryKey(TSysMongoQueryKeys record);
}