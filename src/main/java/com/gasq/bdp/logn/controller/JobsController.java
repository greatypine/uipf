package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gasq.bdp.logn.model.TSysTimerJobconfig;
import com.gasq.bdp.logn.model.TSysTimerJobconfigKey;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfo;
import com.gasq.bdp.logn.service.TSysTimerJobconfigService;
import com.gasq.bdp.logn.service.TSysTimerWfExeInfoService;

@RestController
@RequestMapping(value = "/job")
public class JobsController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerJobconfigService jobconfigService;
    @Autowired
    TSysTimerWfExeInfoService exeInfoService;
    
	@RequestMapping(value = "/saveJobs",method=RequestMethod.POST)
	public Boolean saveJobs(@RequestBody List<Map<String,Object>> data) {
		if(data.size()<=0) return false;
		boolean result = jobconfigService.saveJobs(data);
		return result;
	 }
	@RequestMapping(value = "/removeNode",method=RequestMethod.POST)
	public Boolean removeNode(TSysTimerJobconfig job) {
		boolean result = false;
		try {
			result = jobconfigService.removeNode(job);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return result;
	 }
	@RequestMapping(value="/getWorkFlowNodes",method=RequestMethod.POST)
    public Map<String, Object> getWorkFlowNodes(Integer wfid,Integer logid){
		Map<String,Object> map = new HashMap<String, Object>();
    	try {
    		List<TSysTimerWfExeInfo> listefinfo = null;
    		if(logid>0 &&logid!=null) {
    			TSysTimerWfExeInfo exeInfo = new TSysTimerWfExeInfo();
    			exeInfo.setLogid(logid);
    			listefinfo = exeInfoService.queryList(exeInfo);
    		}
    		map.put("okwf", listefinfo);
    		TSysTimerJobconfigKey jobconfigKey = new TSysTimerJobconfigKey();
    		jobconfigKey.setWorkflowId(wfid);
    		map.put("allwf", jobconfigService.getWorkFlowNodes(jobconfigKey));
    		return map;
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return map;
    }
}

	
	
