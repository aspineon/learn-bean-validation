package com.backcountry.demos.demo04.constraits;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Size(min=1)
@NotNull
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface HasItems {

    String message() default "The order must have at least one item";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
