package com.backcountry.web.services.execptions;

public class InternalServerErrorServiceException extends ServiceException {
    public InternalServerErrorServiceException(String message) {
        super(message);
    }

    public InternalServerErrorServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
