package com.gasq.bdp.logn.service;
import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TMessage;
import com.gasq.bdp.logn.model.TMessageExample;

public interface TMessageService {

    int add(TMessage record);

    List<TMessage> selectByExample(TMessageExample example);

    TMessage selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TMessage bean);

	boolean saveOrUpdate(TMessage bean);

	boolean delete(int id);

}