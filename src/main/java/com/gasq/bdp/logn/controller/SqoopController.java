package com.gasq.bdp.logn.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.TSysTimerJobSqoop;
import com.gasq.bdp.logn.service.TSysTimerJobSqoopService;

@RestController
@RequestMapping(value = "/sqoop")
public class SqoopController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    TSysTimerJobSqoopService sqoopService;
    //错误信息
    Map<String,Object> paramMap = new HashMap<String,Object>();
    
	@RequestMapping(value = "/querySqoops",method=RequestMethod.POST)
	public Map<String, Object> querySqoops(TSysTimerJobSqoop bean) {
		try {
			return sqoopService.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public boolean saveOrUpdate(TSysTimerJobSqoop bean) {
		try {
			return sqoopService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public boolean delete(int id) {
		try {
			return sqoopService.delete(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return false;
	 }
}

	
	
