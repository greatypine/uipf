package com.gasq.bdp.logn.component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.service.CommonService;
import com.gasq.bdp.logn.service.EmailManager;

/**
 * 定时任务配置类
 */
@Component
public class SchedulingCollection {
	@Autowired EmailManager emailService;
	@Autowired CommonService commonService;
	@Autowired TSysUserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Scheduled(cron = "0/5 * * * * ?")
    public String doCountDayOrderChannelCost() {
    	logger.info("定时器【***】后台开始运行........................");
    	logger.info("定时器【***】后台运行完毕!");
    	return "";
    }
	
}