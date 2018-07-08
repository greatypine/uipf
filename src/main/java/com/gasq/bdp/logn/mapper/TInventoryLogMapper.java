package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TInventoryLog;
import com.gasq.bdp.logn.model.TInventoryLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TInventoryLogMapper {
    long countByExample(TInventoryLogExample example);

    int deleteByExample(TInventoryLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TInventoryLog record);

    int insertSelective(TInventoryLog record);

    List<TInventoryLog> selectByExampleWithRowbounds(TInventoryLogExample example, RowBounds rowBounds);

    List<TInventoryLog> selectByExample(TInventoryLogExample example);

    TInventoryLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TInventoryLog record, @Param("example") TInventoryLogExample example);

    int updateByExample(@Param("record") TInventoryLog record, @Param("example") TInventoryLogExample example);

    int updateByPrimaryKeySelective(TInventoryLog record);

    int updateByPrimaryKey(TInventoryLog record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countByBean(Map<String, Object> params);
}