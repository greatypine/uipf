package com.gasq.bdp.logn.iexception;

public class WorkFlowStateException extends Exception{
private static final long serialVersionUID = 1L;
	
	public WorkFlowStateException() {
		super();
	}

	public WorkFlowStateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message);
	}

	public WorkFlowStateException(Throwable cause) {
		super(cause);
	}

	public WorkFlowStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkFlowStateException(String message) {
		super(message);
	}
}
