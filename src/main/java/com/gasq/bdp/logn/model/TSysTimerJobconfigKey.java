package com.gasq.bdp.logn.model;

public class TSysTimerJobconfigKey extends ParamsObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private Integer workflowId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }
}