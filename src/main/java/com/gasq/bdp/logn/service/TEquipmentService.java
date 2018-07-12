package com.gasq.bdp.logn.service;
import java.util.List;
import java.util.Map;

import com.gasq.bdp.logn.model.TEquipment;
import com.gasq.bdp.logn.model.TEquipmentExample;

public interface TEquipmentService {

    int add(TEquipment record);

    List<TEquipment> selectByExample(TEquipmentExample example);

    TEquipment selectByPrimaryKey(Integer id);

    Map<String, Object> queryPagingList(TEquipment bean);

	boolean saveOrUpdate(TEquipment bean);

	boolean delete(int id);

	Map<String, Object> queryAmountSum(TEquipment bean);

}