package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerMongo;
import com.gasq.bdp.logn.model.TSysTimerMongoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerMongoMapper {
    long countByExample(TSysTimerMongoExample example);

    int deleteByExample(TSysTimerMongoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerMongo record);

    int insertSelective(TSysTimerMongo record);

    List<TSysTimerMongo> selectByExampleWithRowbounds(TSysTimerMongoExample example, RowBounds rowBounds);

    List<TSysTimerMongo> selectByExample(TSysTimerMongoExample example);

    TSysTimerMongo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerMongo record, @Param("example") TSysTimerMongoExample example);

    int updateByExample(@Param("record") TSysTimerMongo record, @Param("example") TSysTimerMongoExample example);

    int updateByPrimaryKeySelective(TSysTimerMongo record);

    int updateByPrimaryKey(TSysTimerMongo record);

	List<Map<String, Object>> queryDataBaseTree(@Param("record") TSysTimerMongo bean);
}