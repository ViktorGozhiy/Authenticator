package com.github.viktorgozhiy.authenticator.support;

public abstract class ApiStatus {
    public static final int USER_ALREADY_EXISTS = 5001;
    public static final int UNABLE_TO_SENT_EMAIL_TO_USER = 5002;
    public static final int USER_EMAIL_ALREADY_CONFIRMED = 5003;
    public static final int WRONG_EMAIL_CONFIRMATION_CODE = 5004;
    public static final int USER_NOT_FOUND = 5005;
    public static final int USER_EMAIL_NOT_CONFIRMED = 5006;
    public static final int USER_NOT_ACCEPTED = 5007;
    public static final int FIELD_VALIDATION_ERROR = 5008;
    public static final int REQUEST_NOT_READABLE = 5009;
    public static final int KEY_NOT_FOUND = 5010;
}
