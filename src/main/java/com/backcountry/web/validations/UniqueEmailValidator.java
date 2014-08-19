package com.backcountry.web.validations;

import com.backcountry.web.constraints.UniqueEmail;
import com.backcountry.web.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Edwin Dalorzo.
 */
@Component
@Scope("prototype")
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && customerRepository.findByEmail(value) == null;
    }
}
