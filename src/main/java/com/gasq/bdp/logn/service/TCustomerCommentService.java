package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.model.TCustomerComment;
import com.gasq.bdp.logn.model.TCustomerCommentExample;

public interface TCustomerCommentService {

    boolean saveOrUpdate(TCustomerComment bean) throws WorkFlowStateException;

    List<TCustomerComment> selectByExample(TCustomerCommentExample example);

    Map<String, Object> queryPagingList(TCustomerComment bean);

	boolean delete(Integer id);

}