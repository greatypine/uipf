package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.model.TSysTimerJobDataExport;
import com.gasq.bdp.logn.model.TSysTimerJobDataExportExample;

import java.util.List;
import java.util.Map;

public interface TSysTimerJobDataExportService {

	Map<String, Object> queryPagingList(TSysTimerJobDataExport bean);

	boolean saveOrUpdate(TSysTimerJobDataExport bean);

	boolean delete(int id);

	List<TSysTimerJobDataExport> selectByExample(TSysTimerJobDataExportExample dataExportExample);
	
	long countByExample(TSysTimerJobDataExportExample example);

	List<Map<String, Object>> queryExprotType();
}