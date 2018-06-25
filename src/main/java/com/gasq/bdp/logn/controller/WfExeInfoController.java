package com.gasq.bdp.logn.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gasq.bdp.logn.model.TSysTimerWfExeInfo;
import com.gasq.bdp.logn.service.TSysTimerWfExeInfoService;

@RestController
@RequestMapping(value = "/wfexeinfo")
public class WfExeInfoController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerWfExeInfoService exeInfoService;
    
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
    public List<TSysTimerWfExeInfo> queryList(TSysTimerWfExeInfo exeInfo){
    	try {
    		return exeInfoService.queryList(exeInfo);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    	return null;
    }
}

	
	
