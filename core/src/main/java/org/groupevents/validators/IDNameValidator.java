package org.groupevents.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IDNameValidator implements ConstraintValidator<IDNameConstraint,String>{
private static final Pattern ID_NAME = Pattern.compile("^[A-Za-z][a-zA-z\\d_-]+$");
	@Override
	public boolean isValid(String nameField, ConstraintValidatorContext ctx) {
		return nameField != null && nameField.length() >4 && ID_NAME.matcher(nameField).matches();
	}

}
