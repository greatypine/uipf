package com.gasq.bdp.logn.service;
import com.gasq.bdp.logn.model.TSysRole;
import com.gasq.bdp.logn.model.TSysRoleExample;
import java.util.List;
import java.util.Map;

public interface TSysRoleService {

    int add(TSysRole record);

    List<TSysRole> selectByExample(TSysRoleExample example);

    TSysRole selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysRole bean);

	boolean saveOrUpdate(TSysRole bean) throws Exception;

	boolean delete(int id);
	/**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param id
     * @return
     */
    List<TSysRole> selectRolesByUserId(Long userId);

	List<TSysRole> selectByExample(TSysRole bean);

	List<Map<String, Object>> queryMapBeanList(TSysRole bean);

}