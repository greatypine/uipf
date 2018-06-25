package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.gasq.bdp.logn.model.TScoreExchange;
import com.gasq.bdp.logn.model.TScoreExchangeExample;

public interface TScoreExchangeService {
    long countByExample(TScoreExchangeExample example);

    int deleteByExample(TScoreExchangeExample example);

    boolean delete(Integer id);

    int insert(TScoreExchange record);

    int insertSelective(TScoreExchange record);

    List<TScoreExchange> selectByExampleWithRowbounds(TScoreExchangeExample example, RowBounds rowBounds);

    List<TScoreExchange> selectByExample(TScoreExchangeExample example);

    TScoreExchange selectByPrimaryKey(Integer id);

    int updateByExampleSelective(TScoreExchange record,TScoreExchangeExample example);

    int updateByExample(TScoreExchange record,TScoreExchangeExample example);

    int updateByPrimaryKeySelective(TScoreExchange record);

    int updateByPrimaryKey(TScoreExchange record);

	Map<String, Object> queryPagingList(TScoreExchange bean);

	boolean saveOrUpdate(TScoreExchange bean);
}