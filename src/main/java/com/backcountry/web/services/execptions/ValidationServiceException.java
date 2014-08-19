package com.backcountry.web.services.execptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;


public class ValidationServiceException extends BadRequestServiceException {

    public <T> ValidationServiceException(Set<ConstraintViolation<T>> violations) {
        super(getValidationMessage(violations));
    }

    public ValidationServiceException(BindingResult bindingResult){
        super(getValidationMessage(bindingResult));
    }

    private static String getValidationMessage(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors()
                    .stream()
                    .map(ValidationServiceException::getValidationMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
        }
        return "Unknown validation error";
    }

    private static String getValidationMessage(ObjectError error){
        if(error instanceof FieldError){
            FieldError fieldError = (FieldError) error;
            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s.%s %s: %s", className, property, message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }

    private static <T> String getValidationMessage(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(ValidationServiceException::getValidationMessage)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static <T> String getValidationMessage(ConstraintViolation<T> violation) {
        String className = violation.getRootBeanClass().getSimpleName();
        String property = violation.getPropertyPath().toString();
        Object invalidValue = violation.getInvalidValue();
        String message = violation.getMessage();
        return String.format("%s.%s %s: %s", className, property, message, invalidValue);
    }
}
