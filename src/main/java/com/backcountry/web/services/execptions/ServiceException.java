package com.backcountry.web.services.execptions;


public abstract class ServiceException extends Exception {
    public ServiceException (String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
