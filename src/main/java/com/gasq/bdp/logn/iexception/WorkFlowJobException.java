package com.gasq.bdp.logn.iexception;

public class WorkFlowJobException extends WorkFlowStateException{
private static final long serialVersionUID = 1L;
	
	public WorkFlowJobException() {
		super();
	}

	public WorkFlowJobException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WorkFlowJobException(Throwable cause) {
		super(cause);
	}

	public WorkFlowJobException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkFlowJobException(String message) {
		super(message);
	}
}
