package com.gasq.bdp.logn.service;
import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TVipCustomer;
import com.gasq.bdp.logn.model.TVipCustomerExample;

public interface TVipCustomerService {

    int add(TVipCustomer record);

    List<TVipCustomer> selectByExample(TVipCustomerExample example);

    TVipCustomer selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TVipCustomer bean);

	boolean saveOrUpdate(TVipCustomer bean);

	boolean delete(int id);

	Map<String, Object> sendMessage(TVipCustomer bean) throws WorkFlowJobException;

}