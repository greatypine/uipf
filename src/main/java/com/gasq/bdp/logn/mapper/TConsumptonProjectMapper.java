package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TConsumptonProject;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TConsumptonProjectMapper {
    long countByExample(TConsumptonProjectExample example);

    int deleteByExample(TConsumptonProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TConsumptonProject record);

    int insertSelective(TConsumptonProject record);

    List<TConsumptonProject> selectByExampleWithRowbounds(TConsumptonProjectExample example, RowBounds rowBounds);

    List<TConsumptonProject> selectByExample(TConsumptonProjectExample example);

    TConsumptonProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TConsumptonProject record, @Param("example") TConsumptonProjectExample example);

    int updateByExample(@Param("record") TConsumptonProject record, @Param("example") TConsumptonProjectExample example);

    int updateByPrimaryKeySelective(TConsumptonProject record);

    int updateByPrimaryKey(TConsumptonProject record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);
}