package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TMessage;
import com.gasq.bdp.logn.model.TMessageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TMessageMapper {
    long countByExample(TMessageExample example);

    int deleteByExample(TMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMessage record);

    int insertSelective(TMessage record);

    List<TMessage> selectByExampleWithRowbounds(TMessageExample example, RowBounds rowBounds);

    List<TMessage> selectByExample(TMessageExample example);

    TMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByExample(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKey(TMessage record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countMessageByBean(Map<String, Object> params);
}