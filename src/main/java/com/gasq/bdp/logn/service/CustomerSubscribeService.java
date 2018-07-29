package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TCustomerSubscribe;
import com.gasq.bdp.logn.model.TCustomerSubscribeExample;
import java.util.List;
import java.util.Map;

public interface CustomerSubscribeService {
    long countByExample(TCustomerSubscribeExample example);

    List<TCustomerSubscribe> selectByExample(TCustomerSubscribe bean);

	Map<String, Object> queryPagingList(TCustomerSubscribe bean);

	boolean saveOrUpdate(TCustomerSubscribe bean);

	boolean delete(int id);

	TCustomerSubscribe selectByPrimaryKey(Integer id);

	String querySubscribeReceptionInfo();

	String querySubscribeInfo();

}