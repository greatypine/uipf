package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysViews;
import com.gasq.bdp.logn.model.TSysViewsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysViewsMapper {
    long countByExample(TSysViewsExample example);

    int deleteByExample(TSysViewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysViews record);

    int insertSelective(TSysViews record);

    List<TSysViews> selectByExampleWithRowbounds(TSysViewsExample example, RowBounds rowBounds);

    List<TSysViews> selectByExample(TSysViewsExample example);

    TSysViews selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysViews record, @Param("example") TSysViewsExample example);

    int updateByExample(@Param("record") TSysViews record, @Param("example") TSysViewsExample example);

    int updateByPrimaryKeySelective(TSysViews record);

    int updateByPrimaryKey(TSysViews record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countByBean(Map<String, Object> params);
}