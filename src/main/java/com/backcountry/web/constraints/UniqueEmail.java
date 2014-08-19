package com.backcountry.web.constraints;

import com.backcountry.web.validations.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueEmailValidator.class})
@ReportAsSingleViolation
public @interface UniqueEmail {

    String message() default "The email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
