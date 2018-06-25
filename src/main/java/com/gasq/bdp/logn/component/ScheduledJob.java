package com.gasq.bdp.logn.component;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.service.RemindContext;
import com.gasq.bdp.logn.service.imp.BackgroudRemindHandle;
import com.gasq.bdp.logn.service.imp.ReceptionRemindHandle;
import com.gasq.bdp.logn.state.StateContext;
import com.gasq.bdp.logn.utils.SpringApplicationUtils;
/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.component
 * @creatTime 2017年9月18日下午2:00:14
 * @remark 定时器定时执行任务类
 */
public class ScheduledJob implements Job {
	
	@Override
	public void execute(JobExecutionContext jobcontext) throws JobExecutionException {
		SpringApplicationUtils.getBean(StateContext.class).setStatus(InitProperties.WORKFLOW_STATUS_READY);
		String name = jobcontext.getJobDetail().getKey().getName();
		String group = jobcontext.getJobDetail().getKey().getGroup();
		if(name.equals("executeReceptionRemind")) {
			RemindContext remindContext = new RemindContext(new ReceptionRemindHandle());
			remindContext.exeRemind(name, group);
		}else if(name.equals("executeBackgroundRemind")) {
			RemindContext remindContext = new RemindContext(new BackgroudRemindHandle());
			remindContext.exeRemind(name, group);
		}
	}
}