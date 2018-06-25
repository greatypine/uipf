package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.model.TInventoryExample;

public interface TInventoryService {
    long countByExample(TInventoryExample example);

    List<TInventory> selectByExample(TInventory bean);

	Map<String, Object> queryPagingList(TInventory bean);

	boolean saveOrUpdate(TInventory bean) throws SchedulerException;

	boolean delete(int id);

	TInventory selectByPrimaryKey(Integer id);
	
	Double getInventoryNumbs(Integer id);
	
	Boolean checkInventoryEnable(Integer id,Double numbs) throws SchedulerException;

	Map<String, Object> appendInventory(TInventory bean);

	Map<String, Object> deliveryInventory(TInventory bean);

}