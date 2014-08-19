package com.backcountry.demos.demo04.constraits;

import com.backcountry.demos.demo04.validations.ChronologicalOrderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Edwin Dalorzo.
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ChronologicalOrderValidator.class)
public @interface ChronologicalOrder {

    String message() default "The order dates should be in chronological order";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
