package org.groupevents.controller.utils;

import org.groupevents.service.exceptions.FieldException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;



@Component
public class ExceptionMessageHandler {
   public void handleException(FieldException fieldException,Errors errors) {
	   errors.rejectValue(fieldException.getField(),fieldException.getMessage(), "error in field");
   }
}
