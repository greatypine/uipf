package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysRolePermission;
import com.gasq.bdp.logn.model.TSysRolePermissionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysRolePermissionMapper {
    long countByExample(TSysRolePermissionExample example);

    int deleteByExample(TSysRolePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysRolePermission record);

    int insertSelective(TSysRolePermission record);

    List<TSysRolePermission> selectByExampleWithRowbounds(TSysRolePermissionExample example, RowBounds rowBounds);

    List<TSysRolePermission> selectByExample(TSysRolePermissionExample example);

    TSysRolePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysRolePermission record, @Param("example") TSysRolePermissionExample example);

    int updateByExample(@Param("record") TSysRolePermission record, @Param("example") TSysRolePermissionExample example);

    int updateByPrimaryKeySelective(TSysRolePermission record);

    int updateByPrimaryKey(TSysRolePermission record);

	List<Map<String, Object>> queryMapBeanList(Map<String, Object> json2Map);
}