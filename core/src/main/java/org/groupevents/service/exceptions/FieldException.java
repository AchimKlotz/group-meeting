package org.groupevents.service.exceptions;

public class FieldException extends ServiceException {

	private String field;
	private static final long serialVersionUID = -5999917187309707228L;

	public FieldException() {
		super();
		
	}

	public FieldException(String message, Throwable cause,String field) {
		super(message, cause);
		this.field = field;
	}
	

	public FieldException(String message,String field) {
		super(message);
		this.field=field;
	}

	public String getField() {
		return field;
	}

}
