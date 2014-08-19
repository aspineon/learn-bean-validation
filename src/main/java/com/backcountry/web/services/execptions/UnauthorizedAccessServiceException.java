package com.backcountry.web.services.execptions;

public class UnauthorizedAccessServiceException extends ServiceException {

    public UnauthorizedAccessServiceException(String message) {
        super(message);
    }

    public UnauthorizedAccessServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
