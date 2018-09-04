package com.example.util.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {
    private String sourceField;
    private String targetField;
    private String message;

    @Override
    public void initialize(Confirm constraintAnnotation) {
        sourceField = constraintAnnotation.source();
        targetField = constraintAnnotation.target();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper bean = new BeanWrapperImpl(value);

        Object sourceValue = bean.getPropertyValue(sourceField);
        Object targetValue = bean.getPropertyValue(targetField);

        if (Objects.equals(sourceValue, targetValue)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(sourceField)
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("")
                    .addPropertyNode(targetField)
                    .addConstraintViolation();
            return false;
        }
    }

}
