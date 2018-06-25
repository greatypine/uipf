package com.gasq.bdp.logn.service;
import java.util.List;
import java.util.Map;
import com.gasq.bdp.logn.model.TSysRolePermission;

public interface TSysRolePermissionService {

    int add(TSysRolePermission record);

    List<TSysRolePermission> selectByExample(TSysRolePermission bean);

    TSysRolePermission selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysRolePermission bean);

	boolean saveOrUpdate(TSysRolePermission bean);

	boolean delete(int id);

	List<Map<String, Object>> queryMapBeanList(TSysRolePermission bean);

}