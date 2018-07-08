package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.model.TInventoryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TInventoryMapper {
    long countByExample(TInventoryExample example);

    int deleteByExample(TInventoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TInventory record);

    int insertSelective(TInventory record);

    List<TInventory> selectByExampleWithRowbounds(TInventoryExample example, RowBounds rowBounds);

    List<TInventory> selectByExample(TInventoryExample example);

    TInventory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TInventory record, @Param("example") TInventoryExample example);

    int updateByExample(@Param("record") TInventory record, @Param("example") TInventoryExample example);

    int updateByPrimaryKeySelective(TInventory record);

    int updateByPrimaryKey(TInventory record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countByBean(Map<String, Object> params);
}