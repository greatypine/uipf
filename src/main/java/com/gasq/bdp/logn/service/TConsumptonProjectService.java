package com.gasq.bdp.logn.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.gasq.bdp.logn.model.TConsumptonProject;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;

public interface TConsumptonProjectService {
    
    long countByExample(TConsumptonProjectExample example);

    List<TConsumptonProject> selectByExample(TConsumptonProject bean);

	Map<String, Object> queryPagingList(TConsumptonProject bean);

	Map<String, Object> saveOrUpdate(TConsumptonProject bean) throws SchedulerException;

	boolean delete(int id);

	TConsumptonProject selectByPrimaryKey(Integer id);
	
	void deleteByExample(TConsumptonProjectExample bean);
}