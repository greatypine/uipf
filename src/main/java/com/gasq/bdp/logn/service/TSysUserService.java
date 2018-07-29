package com.gasq.bdp.logn.service;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.SystemUser;
import com.gasq.bdp.logn.model.TSysUser;
import java.util.List;
import java.util.Map;

public interface TSysUserService {

    int add(TSysUser record);

    List<TSysUser> selectByExample(TSysUser bean);

    TSysUser selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TSysUser bean);

	Integer saveOrUpdate(TSysUser bean) throws WorkFlowStateException;

	boolean delete(int id);

	SystemUser queryFullUser(TSysUser user);

	boolean unlockSubmit(TSysUser bean);

}