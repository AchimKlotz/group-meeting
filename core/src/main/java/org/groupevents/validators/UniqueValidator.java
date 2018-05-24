package org.groupevents.validators;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import javax.persistence.Id;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.groupevents.validators.utils.ApplicationContextProvider;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.DirectFieldAccessor;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

	private FieldValueExists service;
	private String[] fields;

	@Override
	public void initialize(Unique unique) {
		Class<? extends FieldValueExists> clazz = unique.service();

		String serviceQualifier = unique.serviceQualifier();

		if (!serviceQualifier.equals("")) {
			this.service = (FieldValueExists) ApplicationContextProvider.getBean(serviceQualifier, clazz);
		} else {
			this.service = (FieldValueExists) ApplicationContextProvider.getBean(clazz);
		}

		fields = unique.fields();

	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext ctx) {

		Reflections r = new Reflections(object.getClass().getPackage().getName(),new FieldAnnotationsScanner());
		Set<Field> idFields = r.getFieldsAnnotatedWith(Id.class);
		DirectFieldAccessor dfa = new DirectFieldAccessor(object);
		for (Field field : idFields) {
			try {
				if (dfa.getPropertyValue(field.getName())!=null && !dfa.getPropertyValue(field.getName()).toString().equals("0")) {
					return true;
				}
			} catch (IllegalArgumentException e ) {
				throw new RuntimeException(e);
			}

		}
		
		for (String fieldName : fields) {
			Object value = dfa.getPropertyValue(fieldName);
			if (service.fieldValueExists(value, fieldName)) {
				return false;
			}
		}
		return true;
	}

}
