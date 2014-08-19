package com.backcountry.demos.demo04.constraits;

import com.backcountry.demos.demo04.model.OrderStatus;
import com.backcountry.demos.demo04.validations.HasStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Edwin Dalorzo.
 */
@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HasStatusValidator.class)
public @interface HasStatus {

    OrderStatus value();

    String message() default "It should have status '{value}'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
