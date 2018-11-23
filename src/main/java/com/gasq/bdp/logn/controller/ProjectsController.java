package com.gasq.bdp.logn.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gasq.bdp.logn.model.TProject;
import com.gasq.bdp.logn.service.TSysProjectsService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/project")
@Api(value="商品controller",tags={"商品管理接口"})
public class ProjectsController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired TSysProjectsService service;
    
	@RequestMapping(value = "/queryList",method=RequestMethod.POST)
	public Map<String, Object> queryMapLists(TProject bean) {
		try {
			return service.queryPagingList(bean);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
    	return null;
	 }
}

	
	
