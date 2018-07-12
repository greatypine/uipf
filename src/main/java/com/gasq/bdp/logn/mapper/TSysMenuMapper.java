package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysMenu;
import com.gasq.bdp.logn.model.TSysMenuExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysMenuMapper {
    long countByExample(TSysMenuExample example);

    int deleteByExample(TSysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysMenu record);

    int insertSelective(TSysMenu record);

    List<TSysMenu> selectByExampleWithRowbounds(TSysMenuExample example, RowBounds rowBounds);

    List<TSysMenu> selectByExample(TSysMenuExample example);

    TSysMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysMenu record, @Param("example") TSysMenuExample example);

    int updateByExample(@Param("record") TSysMenu record, @Param("example") TSysMenuExample example);

    int updateByPrimaryKeySelective(TSysMenu record);

    int updateByPrimaryKey(TSysMenu record);

	List<TSysMenu> selectMenuBeanByRole(Map<String, Object> params);

	List<Map<String, Object>> selectAllMenusByRole(@Param("pid") Integer pid,@Param("roleid") Integer roleid,@Param("companyid") int companyid);

	String getNextCode(@Param("pcode") String code, @Param("companyid") Integer companyid);

	Integer getMaxId();

	List<Map<String, Object>> selectAllMenusTreeByRole(@Param("roleid") Integer roleid,@Param("companyid") int companyid);
}