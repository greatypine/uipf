package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TSysUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysUserMapper {
    long countByExample(TSysUserExample example);

    int deleteByExample(TSysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSysUser record);

    int insertSelective(TSysUser record);

    List<TSysUser> selectByExampleWithRowbounds(TSysUserExample example, RowBounds rowBounds);

    List<TSysUser> selectByExample(TSysUserExample example);

    TSysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSysUser record, @Param("example") TSysUserExample example);

    int updateByExample(@Param("record") TSysUser record, @Param("example") TSysUserExample example);

    int updateByPrimaryKeySelective(TSysUser record);

    int updateByPrimaryKey(TSysUser record);

	List<Map<String, Object>> queryPagingList(Map<String, Object> params);

	Integer countByBean(Map<String, Object> params);
}