package com.gasq.bdp.logn.state;

import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.job.IJob;
import com.gasq.bdp.logn.model.PagingBean;

/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.state
 * @creatTime 2017年9月8日下午4:24:44
 * @remark
 */
@Component
public abstract class WorkFlowState {
	// 定义一个环境角色，也就是封装状态的变换引起的功能变化
	protected StateContext context;
	
    public abstract IJob getJob();

    public abstract Object run(int excute_id, Object inputObj, PagingBean pb) throws WorkFlowStateException;
    
    public abstract Object run(int excute_id, Object inputObj) throws WorkFlowStateException;

    public abstract void stop();

    public void setContext(StateContext _context) {
        this.context = _context;
    }
	public abstract StateContext getContext();

}