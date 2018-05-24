package org.groupevents.validators;

import org.groupevents.dto.common.HasPassword;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		if (HasPassword.class.isAssignableFrom(arg0))
			return true;
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "required.confirmedPassword",
				"Field name is required.");

		HasPassword hasPassword = (HasPassword) target;
		if (!hasPassword.getPassword().equals(hasPassword.getConfirmedPassword())) {
			errors.rejectValue("password", "password.notmatch", "passwords doesn't match");
			return;
		}
		if (!hasPassword.getPassword().matches(".*[A-Z].*")) {
			errors.rejectValue("password", "password.nouppercase", "password doesn't contain an uppercase letter");
		}
		if (!hasPassword.getPassword().matches(".*[a-z].*")) {
			errors.rejectValue("password", "password.nolowercase", "password doesn't contain an lowercaser letter");
		}
		if (!hasPassword.getPassword().matches(".*\\d.*")) {
			errors.rejectValue("password", "password.nodigit", "password doesn't contain a digit");
		}

	}

}
