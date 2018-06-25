package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerJobDataImport;
import com.gasq.bdp.logn.model.TSysTimerJobDataImportExample;

import java.util.List;
import java.util.Map;

public interface TSysTimerJobDataImportService {

	Map<String, Object> queryPagingList(TSysTimerJobDataImport bean);

	boolean saveOrUpdate(TSysTimerJobDataImport bean);

	boolean delete(int id);

	List<TSysTimerJobDataImport> selectByExample(TSysTimerJobDataImportExample example);
	
	long countByExample(TSysTimerJobDataImportExample example);
}