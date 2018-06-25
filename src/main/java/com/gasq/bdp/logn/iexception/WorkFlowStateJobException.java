package com.gasq.bdp.logn.iexception;

public class WorkFlowStateJobException extends Exception{
private static final long serialVersionUID = 1L;
	
	public WorkFlowStateJobException() {
		super();
	}

	public WorkFlowStateJobException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WorkFlowStateJobException(Throwable cause) {
		super(cause);
	}

	public WorkFlowStateJobException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkFlowStateJobException(String message) {
		super(message);
	}
}
