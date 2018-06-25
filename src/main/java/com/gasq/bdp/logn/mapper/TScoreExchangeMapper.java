package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TScoreExchange;
import com.gasq.bdp.logn.model.TScoreExchangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TScoreExchangeMapper {
    long countByExample(TScoreExchangeExample example);

    int deleteByExample(TScoreExchangeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TScoreExchange record);

    int insertSelective(TScoreExchange record);

    List<TScoreExchange> selectByExampleWithRowbounds(TScoreExchangeExample example, RowBounds rowBounds);

    List<TScoreExchange> selectByExample(TScoreExchangeExample example);

    TScoreExchange selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TScoreExchange record, @Param("example") TScoreExchangeExample example);

    int updateByExample(@Param("record") TScoreExchange record, @Param("example") TScoreExchangeExample example);

    int updateByPrimaryKeySelective(TScoreExchange record);

    int updateByPrimaryKey(TScoreExchange record);
}