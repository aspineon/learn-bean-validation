package com.backcountry.demos;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Edwin Dalorzo.
 */
public class Utils {

    public static Date date(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> void printErrors(Set<? extends ConstraintViolation<T>> violations) {
        Formatter out = new Formatter();
        for (ConstraintViolation<T> violation : violations) {
            String className = violation.getRootBeanClass().getSimpleName();
            String property = violation.getPropertyPath().toString();
            Object invalidValue = violation.getInvalidValue();
            String message = violation.getMessage();
            if (!violation.getConstraintDescriptor().getPayload().isEmpty()) {
                out.format("Severity %s:%n", violation.getConstraintDescriptor()
                        .getPayload()
                        .stream()
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(", ")));
            }
            out.format("%s.%s %s: %s%n", className, property, message, invalidValue);
        }
        System.out.println(out.toString());
    }
}
