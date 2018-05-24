package org.groupevents.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=UniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
	String message() default "Invalid name. Must start with a letter and is not allowed to contain special characters or whitespaces";
	String[] fields();
	Class<? extends Payload>[] payload() default {};
	Class<?>[] groups() default {};
	Class<? extends FieldValueExists> service();
	String serviceQualifier() default "";

}
