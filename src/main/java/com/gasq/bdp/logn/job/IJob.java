package com.gasq.bdp.logn.job;

import java.io.Serializable;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.ParamsObject;

/**
 * 
 * @author Ju_weigang
 * @时间 2017年8月29日下午5:16:45
 * @项目路径 com.gasq.bdp.logn.job
 * @描述
 */
public interface IJob extends Serializable {
	/**
	 * 执行JOB任务
	 * @param map 
	 * @return 
	 * @throws Exception
	 */
	public Object execute(ParamsObject params, Object inputObj) throws WorkFlowJobException;
	/**
	 * 执行JOB任务
	 * @param map 
	 * @return 
	 * @throws  
	 * @throws Exception
	 */
	public Object execute(ParamsObject params, Object inputObj, PagingBean pb) throws WorkFlowJobException;
	
	/**
	 * 释放资源
	 * @throws WorkFlowJobException 
	 */
	public void destory() throws WorkFlowJobException;

}
