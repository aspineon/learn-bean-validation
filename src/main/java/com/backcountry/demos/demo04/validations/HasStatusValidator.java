package com.backcountry.demos.demo04.validations;

import com.backcountry.demos.demo04.model.OrderStatus;
import com.backcountry.demos.demo04.constraits.HasStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Edwin Dalorzo.
 */
public class HasStatusValidator implements ConstraintValidator<HasStatus, OrderStatus>  {

    private OrderStatus status;

    @Override
    public void initialize(HasStatus expected) {
        this.status = expected.value();
    }

    @Override
    public boolean isValid(OrderStatus actual, ConstraintValidatorContext context) {
        return this.status.equals(actual);
    }
}
