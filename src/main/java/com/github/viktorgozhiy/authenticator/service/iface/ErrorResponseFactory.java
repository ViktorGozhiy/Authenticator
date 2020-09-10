package com.github.viktorgozhiy.authenticator.service.iface;

import com.github.viktorgozhiy.authenticator.support.ErrorResponse;

public interface ErrorResponseFactory {

    ErrorResponse getErrorResponse(String key, int code, Object... objects);
    ErrorResponse getErrorResponse(String key, int code);
}
