package com.gasq.bdp.logn.provider;

import java.util.Date;

import com.gasq.bdp.logn.model.TimmerLog;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author justin 
 * @2017年1月14日 下午3:46:52
 * @TODO 注释：
 */
public class LogProvider {
	/**
	 * 查询定时器日志
	 * @param 日志对象
	 * @return
	 */
	public static String getTimmerLogsProvider(TimmerLog timmerLog){
		StringBuffer buffer = new StringBuffer("SELECT tl.* from t_sys_timer_log tl where 1=1 ");
		if(CommonUtils.isEmpty(timmerLog)) return buffer.toString();
		if((timmerLog.getId()!=null)&&(timmerLog.getId()>0)){
			buffer.append(" and tl.id =" + timmerLog.getId());
		}
		if(!CommonUtils.isEmpty(timmerLog.getMessage())){
			buffer.append(" and tl.message like CONCAT('%',"+timmerLog.getMessage()+",'%') ");
		}
		if(!CommonUtils.isEmpty(timmerLog.getStatus())){
			buffer.append(" and tl.status =" +timmerLog.getStatus());
		}
		if(!CommonUtils.isEmpty(timmerLog.getScid())){
			buffer.append(" and tl.scid =" +timmerLog.getScid());
		}
		if(!CommonUtils.isEmpty(timmerLog.getJobid())){
			buffer.append(" and tl.jobid =" +timmerLog.getJobid());
		}
		if(!CommonUtils.isEmpty(timmerLog.getCrateTime())){
			Date date = timmerLog.getCrateTime();
			String datestr = DateUtil.getDateStr(date, DateUtil.DATE_DEFAULT_FORMAT);
			buffer.append(" and tl.createTime >='" +datestr+"'");
		}
		return buffer.toString();
	}
	
}
