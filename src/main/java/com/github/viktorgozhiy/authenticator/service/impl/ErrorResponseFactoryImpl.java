package com.github.viktorgozhiy.authenticator.service.impl;

import com.github.viktorgozhiy.authenticator.service.iface.ErrorResponseFactory;
import com.github.viktorgozhiy.authenticator.support.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ErrorResponseFactoryImpl implements ErrorResponseFactory {

    @Autowired
    private MessageSource messageSource;

    Logger logger = LoggerFactory.getLogger(ErrorResponseFactoryImpl.class);

    @Override
    public ErrorResponse getErrorResponse(String key, int code, Object... objects) {
        String message;
        Locale locale = LocaleContextHolder.getLocale();
        try {
            message = messageSource.getMessage(key, objects, locale);
        } catch (NoSuchMessageException ex) {
            logger.warn("NoSuchMessage" + ex);
            message = key;
        }
        return new ErrorResponse(message, code);
    }

    @Override
    public ErrorResponse getErrorResponse(String key, int code) {
        return getErrorResponse(key, code, null);
    }
}
