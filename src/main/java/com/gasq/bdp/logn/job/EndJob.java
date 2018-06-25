/**
 * 
 */
package com.gasq.bdp.logn.job;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.ParamsObject;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.job
 * @creatTime 2017年11月30日上午11:13:13
 * @remark
 */
@Component
public class EndJob implements IJob{
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(EndJob.class);
	
	public Object execute(ParamsObject paramsObject, Object inputObj, PagingBean pb) throws WorkFlowJobException{
		return null;
	}

	public void destory() {
	}

	@Override
	public Object execute(ParamsObject params, Object inputObj) throws WorkFlowJobException {
		return null;
	}

}
