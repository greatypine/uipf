package com.gasq.bdp.logn.component;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.model.TSysTimerLog;
import com.gasq.bdp.logn.model.TSysTimerLogExample;
import com.gasq.bdp.logn.service.TSysTimerLogService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.component
 * @creatTime 2017年9月26日下午4:35:08
 * @remark
 */
@Component
public class ScheduleService {
	@Autowired
	private TSysTimerLogService logService;
	
	/**
	 * 检查定时器依赖
	 * 如果日志表中有存在所依赖的成功记录信息 则返回成功
	 * @param id
	 * @return
	 */
	public boolean checkScheduleDependentEnable(int id) {
		TSysTimerLogExample sqlqueryExample = new TSysTimerLogExample();
		sqlqueryExample.createCriteria().andScidEqualTo(id).andStatusEqualTo(1).andTypeEqualTo(0).andCreatetimeGreaterThanOrEqualTo(DateUtil.parseStr(DateUtil.getCurrentDate()));
		List<TSysTimerLog> timers = logService.selectByExample(sqlqueryExample);
		return timers.size()>0?true:false;
	}
}
