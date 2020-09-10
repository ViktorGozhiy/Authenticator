package com.github.viktorgozhiy.authenticator.support;

import com.github.viktorgozhiy.authenticator.service.iface.ErrorResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        logger.debug("REST_EXCEPTION", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getBindingResult().getFieldError().getDefaultMessage(), ApiStatus.FIELD_VALIDATION_ERROR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handle(RestException ex) {
        logger.debug("REST_EXCEPTION", ex);
        ErrorResponse errorResponse = ex.getErrorResponse();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException ex) {
        logger.debug("BAD_REQUEST", ex);
        ErrorResponse errorResponse = errorResponseFactory.getErrorResponse(MESSAGES.ERR_REQUEST_NOT_READABLE, ApiStatus.REQUEST_NOT_READABLE, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        logger.error("Unhandled error", ex);
        ErrorResponse errorResponse = errorResponseFactory.getErrorResponse(ex.getMessage(), 0, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
