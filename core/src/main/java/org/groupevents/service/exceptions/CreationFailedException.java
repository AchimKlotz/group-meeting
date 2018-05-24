package org.groupevents.service.exceptions;

public class CreationFailedException extends ServiceException {

	public CreationFailedException() {
		super();		
	}

	public CreationFailedException(String message, Throwable cause) {
		super(message, cause);		
	}

	public CreationFailedException(String message) {
		super(message);		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7515081042757624831L;

}
