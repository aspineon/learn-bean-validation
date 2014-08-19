package com.backcountry.web.services.execptions;

public class BadRequestServiceException extends ServiceException {
    public BadRequestServiceException (String message) {
        super(message);
    }

    public BadRequestServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
