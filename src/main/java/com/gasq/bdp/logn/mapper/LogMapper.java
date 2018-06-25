package com.gasq.bdp.logn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import com.gasq.bdp.logn.model.TimmerLog;
import com.gasq.bdp.logn.provider.LogProvider;

/**
 * 
 * @author: Justin
 * @createTime: 2017年7月7日上午9:37:26
 * @packageName: cn.gnkj.mapper.gabdp
 * @remark:
 */
public interface LogMapper {
	@SelectProvider(type=LogProvider.class,method="getTimmerLogsProvider")
	List<TimmerLog> getTimmerLogsProvider(TimmerLog timmerLog)throws Exception;
	@Insert("insert into t_sys_timer_log(message,modelname,status,jobid,wfid,scid)values(#{message},#{modelname},#{status},#{jobid},#{wfid},#{scid})")
	void add(TimmerLog timmerLog)throws Exception;
}
