package com.backcountry.web.controllers;

import com.backcountry.web.model.RestError;
import com.backcountry.web.services.execptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Edwin Dalorzo.
 */
public abstract class BaseRestController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new RestError(e);
    }

    @ExceptionHandler(BadRequestServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestError handleBadRequestServiceException(BadRequestServiceException e) {
        return new RestError(e);
    }

    @ExceptionHandler(UnauthorizedAccessServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestError handleUnauthorizedAccessServiceException(UnauthorizedAccessServiceException e) {
        return new RestError(e);
    }

    @ExceptionHandler(ResourceNotFoundServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestError handleNotFoundServiceException(ResourceNotFoundServiceException e) {
        return new RestError(e);
    }

    @ExceptionHandler(InternalServerErrorServiceException.class)
    public RestError handleInternalServerErrorServiceException(InternalServerErrorServiceException e) {
        return new RestError(e);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestError handleServiceException(ServiceException e) {
        return new RestError(e);
    }
}
