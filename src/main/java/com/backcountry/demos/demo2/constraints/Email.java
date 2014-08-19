package com.backcountry.demos.demo2.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented

@Size(min=5)
@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")

@Constraint(validatedBy = {})
//@ReportAsSingleViolation

public @interface Email {

    String message() default "Invalid email address"; //demo i18n also!
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
