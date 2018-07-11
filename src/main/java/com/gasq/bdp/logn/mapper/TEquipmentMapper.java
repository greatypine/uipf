package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TEquipment;
import com.gasq.bdp.logn.model.TEquipmentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TEquipmentMapper {
    long countByExample(TEquipmentExample example);

    int deleteByExample(TEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TEquipment record);

    int insertSelective(TEquipment record);

    List<TEquipment> selectByExampleWithRowbounds(TEquipmentExample example, RowBounds rowBounds);

    List<TEquipment> selectByExample(TEquipmentExample example);

    TEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TEquipment record, @Param("example") TEquipmentExample example);

    int updateByExample(@Param("record") TEquipment record, @Param("example") TEquipmentExample example);

    int updateByPrimaryKeySelective(TEquipment record);

    int updateByPrimaryKey(TEquipment record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countByBean(Map<String, Object> params);
}