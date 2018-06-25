package com.gasq.bdp.logn.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.job.IJob;
import com.gasq.bdp.logn.model.PagingBean;
/**
 * 
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.state
 * @creatTime 2017年9月8日下午4:24:32
 * @remark
 */
@Component
public class StateContext {
	protected Logger log = LoggerFactory.getLogger(getClass());
	@Autowired StartWorkFlow startWorkFlow;
	@Autowired EndWorkFlow endWorkFlow;
	@Autowired SqlQueryOptionWorkFlow queryOptionWorkFlow;
	@Autowired SqlInsertOrUpdateWorkFlow insertOrUpdateWorkFlow;
	@Autowired SqlOptionWorkFlow sqlOptionWorkFlow;
	@Autowired DataExportWorkFlow excelExportWorkFlow;
    /**
     * 运行状态
     */
    private int status;
    private int exetype;
    private String taskname;
    private WorkFlowState workFlowState;
    public WorkFlowState getWorkFlowState() {
		return workFlowState;
	}
	public void setWorkFlowState(WorkFlowState workFlowState) {
		this.workFlowState = workFlowState;
		// 把当前的环境通知到各个实现类中
        this.workFlowState.setContext(this);
	}
	public Object run(int excute_id,Object inputObj, PagingBean pb) throws WorkFlowStateException{
		return this.workFlowState.run(excute_id,inputObj,pb);
	}

    public void stop() {
    	this.workFlowState.stop();
    }
    public IJob getJob() {
    	return this.workFlowState.getJob();
    }
    public StateContext getContext() {
    	return this.workFlowState.getContext();
    }
    public StateContext() {
		this.status = 1;
		this.exetype = 0;
		this.taskname = "";
    }
	public String getStatusName() {
		if(this.status==1) {
			return "就绪中";
		}else if(this.status==2) {
			return "运行中";
		}else if(this.status==99) {
			return "停止";
		}
		return "";
	}
	public int getStatus() {
		return this.status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public int getExetype() {
		return exetype;
	}

	public void setExetype(int exetype) {
		this.exetype = exetype;
	}
}