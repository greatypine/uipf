package com.gasq.bdp.logn.component;

import java.util.List;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.mapper.TSysTimerScheduleconfigMapper;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfig;
import com.gasq.bdp.logn.model.TSysTimerScheduleconfigExample;


@Component
public class MyScheduler {
	
	private static final Logger logger =  LoggerFactory.getLogger(MyScheduler.class); 
	
	@Autowired
	TSysTimerScheduleconfigMapper scheduleconfigMapper;
	
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    
    public void scheduleJobs() throws SchedulerException {
		List<TSysTimerScheduleconfig> listcrons = null;
		try {
			TSysTimerScheduleconfigExample scheduleconfigExample = new TSysTimerScheduleconfigExample();
			scheduleconfigExample.createCriteria().andStatusEqualTo(true);
			listcrons = scheduleconfigMapper.selectByExample(scheduleconfigExample);
			if(listcrons!=null && listcrons.size()>0){
				for (TSysTimerScheduleconfig scheduleconfig : listcrons) {
					addJob(scheduleconfig);
				}
			}
		} catch (Exception e) {
			logger.info("job初始化错误！",e);
		}
    }
    
    public void addJob(TSysTimerScheduleconfig scheduleconfig) throws SchedulerException{
    	Scheduler scheduler = schedulerFactoryBean.getScheduler();
		startJob1(scheduler,scheduleconfig);
    }
    private void startJob1(Scheduler scheduler,TSysTimerScheduleconfig scheduleconfig) throws SchedulerException{
    	String group = scheduleconfig.getGroupname();
    	String name = scheduleconfig.getName();
    	String cron = scheduleconfig.getCron();
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity(name, group).build(); 
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron); 
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(name, group) .withSchedule(scheduleBuilder).build(); 
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

	public boolean removeJob(TSysTimerScheduleconfig scheduleconfig) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		return removeJobTasks(scheduler,scheduleconfig);
	}

	private boolean removeJobTasks(Scheduler scheduler, TSysTimerScheduleconfig scheduleconfig) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity(scheduleconfig.getName(), scheduleconfig.getGroupname()).build();
		return scheduler.deleteJob(jobDetail.getKey());
	} 
}