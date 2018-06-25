package com.gasq.bdp.logn.iexception;

public class InterfaceTypeException extends WorkFlowStateException{
private static final long serialVersionUID = 1L;
	
	public InterfaceTypeException() {
		super();
	}

	public InterfaceTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InterfaceTypeException(Throwable cause) {
		super(cause);
	}

	public InterfaceTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InterfaceTypeException(String message) {
		super(message);
	}
}
