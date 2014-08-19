package com.backcountry.web.services.execptions;


public final class ResourceNotFoundServiceException extends ServiceException {

    public ResourceNotFoundServiceException(String message) {
        super(message);
    }

    public ResourceNotFoundServiceException(String message, Throwable root) {
        super(message);
    }

}
