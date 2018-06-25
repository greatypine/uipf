package com.gasq.bdp.logn.model;

public class TSysTimerScheduleWorkflowKey extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer scheduleId;

    private Integer workflowId;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }
}