package com.github.viktorgozhiy.authenticator.support;

public abstract class MESSAGES {

    // Custom error messages in validation annotations should be with curly braces {}
    public static final String ERR_USERNAME_NOT_BLANK = "{error.username.notblank}";
    public static final String ERR_PASSWORD_NOT_BLANK = "{error.password.notblank}";
    public static final String ERR_PASSWORD_INVALID = "{error.password.invalid}";
    public static final String ERR_EMAIL_INVALID = "{error.email.invalid}";
    public static final String ERR_KEY_LABEL_NOT_BLANK = "{error.key.label.notblank}";
    public static final String ERR_KEY_SECRET_KEY_NOT_BLANK = "{error.key.secretkey.notblank}";
    public static final String ERR_KEY_ALGORITHM_NOT_BLANK = "{error.key.algorithm.notblank}";
    //-------
    public static final String ERR_USER_NOT_ACCEPTED = "error.user.not.accepted";
    public static final String ERR_USER_NOT_FOUND = "error.user.not.found";
    public static final String ERR_REQUEST_NOT_READABLE = "error.request.notreadable";
    public static final String ERR_INTERNAL_SERVER_ERROR = "error.internalservererror";
    public static final String ERR_EMAIL_CONFIRMATION_CODE_NOT_BLANK = "error.email.confirmationcode.notblank";
    public static final String ERR_USER_ALREADY_EXISTS = "error.user.alreadyexists";
    public static final String ERR_USER_EMAIL_ALREADY_CONFIRMED = "error.user.email.alreadyconfirmed";
    public static final String ERR_USER_WRONG_EMAIL_CONFIRMATION_CODE = "error.user.wrongconfirmationcode";
    public static final String ERR_USER_EMAIL_NOT_CONFIRMED = "error.user.email.notconfirmed";
    public static final String ERR_KEY_NOT_FOUND = "error.key.not.found";

    public static final String MESS_CONFIRMATION_CODE_SUBJECT = "mess.confirmationcode.subject";
    public static final String MESS_CONFIRMATION_CODE_PREFIX = "mess.confirmationcode.prefix";
    public static final String MESS_PASSWORD_RECOVERY_SUBJECT = "mess.passwordrecovery.subject";
    public static final String MESS_PASSWORD_RECOVERY_PREFIX = "mess.passwordrecovery.prefix";
}
