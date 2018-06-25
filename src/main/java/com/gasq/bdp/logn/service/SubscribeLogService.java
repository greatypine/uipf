package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TCustomerSubscribeLog;
import com.gasq.bdp.logn.model.TCustomerSubscribeLogExample;
import java.util.List;
import java.util.Map;

public interface SubscribeLogService {
    long countByExample(TCustomerSubscribeLogExample example);

    List<TCustomerSubscribeLog> selectByExample(TCustomerSubscribeLog bean);

	Map<String, Object> queryPagingList(TCustomerSubscribeLog bean);

	boolean saveOrUpdate(TCustomerSubscribeLog bean);

	boolean delete(int id);

	TCustomerSubscribeLog selectByPrimaryKey(Integer id);
}