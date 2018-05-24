package org.groupevents.service.exceptions;

public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7094943413173367760L;
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public ServiceException() {
		super();
	}

}
