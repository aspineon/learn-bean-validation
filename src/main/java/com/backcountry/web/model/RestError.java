package com.backcountry.web.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class RestError {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String message;

    private RestError(String message) {
        this.message = message;
    }

    public RestError(Exception e) {
        this(e.getMessage());
        logError(e);
    }

    public String getMessage() {
        return message;
    }


    private void logError(Throwable throwable) {
        StringWriter out = new StringWriter();
        try (PrintWriter printer = new PrintWriter(out)) {
            throwable.printStackTrace(printer);
        }
        logger.error(out.toString());
    }

}
