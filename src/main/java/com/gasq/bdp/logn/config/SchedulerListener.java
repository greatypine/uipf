package com.gasq.bdp.logn.config;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.gasq.bdp.logn.component.MyScheduler;
/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.config
 * @creatTime 2017年9月12日下午3:37:28
 * @remark
 */
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    public MyScheduler myScheduler;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) { 
        try { 
            myScheduler.scheduleJobs(); 
        } catch (SchedulerException e) { 
        	logger.debug("初始化定时器JOBS出错,错误信息："+e.getMessage(), e); 
        }
     } 
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean(); 
        return schedulerFactoryBean; 
    }
}