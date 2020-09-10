package com.github.viktorgozhiy.authenticator.support;

import org.springframework.http.HttpStatus;

public class RestException extends Exception {

    private final HttpStatus httpStatus;
    private final ErrorResponse errorResponse;

    public RestException(ErrorResponse errorResponse, HttpStatus httpStatus) {
        this.errorResponse = errorResponse;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
