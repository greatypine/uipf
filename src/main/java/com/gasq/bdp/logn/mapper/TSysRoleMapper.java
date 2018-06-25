package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysRoleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysRoleMapper {
    long countByExample(TSysRoleExample example);

    int deleteByExample(TSysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysRole record);

    int insertSelective(TSysRole record);

    List<TSysRole> selectByExampleWithRowbounds(TSysRoleExample example, RowBounds rowBounds);

    List<TSysRole> selectByExample(TSysRoleExample example);

    TSysRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysRole record, @Param("example") TSysRoleExample example);

    int updateByExample(@Param("record") TSysRole record, @Param("example") TSysRoleExample example);

    int updateByPrimaryKeySelective(TSysRole record);

    int updateByPrimaryKey(TSysRole record);

	List<TSysRole> selectRolesByUserId(@Param("id")Long userId);

	List<Map<String, Object>> queryMapBeanList(@Param("record") TSysRole bean);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);
}