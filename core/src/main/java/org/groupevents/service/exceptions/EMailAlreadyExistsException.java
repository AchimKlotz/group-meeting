package org.groupevents.service.exceptions;

public class EMailAlreadyExistsException extends ServiceException {

	public EMailAlreadyExistsException() {
		super();		
	}

	public EMailAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);		
	}

	public EMailAlreadyExistsException(String message) {
		super(message);		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7515081042757624831L;

}
