package com.gasq.bdp.logn.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.state.StateContext;


/**
 * 定时任务配置类
 */
@Component
public class SchedulingCollection {
	@Autowired StateContext stateContext;
	@Autowired EmailManager emailService;

//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Scheduled(cron = "0/1 * * * * ?") // 每天1点执行
//    public String doCountOrderCost() {
//    	logger.info("---------------JOB [工作流运行情况] --------------"+InterfaceType.getValueName(stateContext.getExetype())+stateContext.getTaskname()+stateContext.getStatusName());
//    	return "";
//    }

}