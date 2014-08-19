package com.backcountry.demos.demo04.validations;


import com.backcountry.demos.demo04.constraits.ChronologicalOrder;
import com.backcountry.demos.demo04.model.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChronologicalOrderValidator implements ConstraintValidator<ChronologicalOrder, Order> {


    @Override
    public void initialize(ChronologicalOrder constraintAnnotation) {
    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext context) {
        return order.getCreationDate().getTime() <= order.getPaymentDate().getTime() &&
                order.getPaymentDate().getTime() < order.getDeliveryDate().getTime();
    }
}
