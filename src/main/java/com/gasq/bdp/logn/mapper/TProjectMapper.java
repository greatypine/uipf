package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.model.TProjectExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TProjectMapper {
    long countByExample(TProjectExample example);

    int deleteByExample(TProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TProject record);

    int insertSelective(TProject record);

    List<TProject> selectByExampleWithRowbounds(TProjectExample example, RowBounds rowBounds);

    List<TProject> selectByExample(TProjectExample example);

    TProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TProject record, @Param("example") TProjectExample example);

    int updateByExample(@Param("record") TProject record, @Param("example") TProjectExample example);

    int updateByPrimaryKeySelective(TProject record);

    int updateByPrimaryKey(TProject record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);
}