package com.github.viktorgozhiy.authenticator.support;

public abstract class API {

    public static final String API = "/api/";
    public static final String API_SIGNUP = API + "signup/";
    public static final String API_RECOVERY = API + "recovery/";
    public static final String API_CONFIRM = API + "confirm/";
    public static final String API_SEND_CODE = API + "code/";

    public static final String API_U = API + "u/";
    public static final String API_KEY_LIST = API_U + "key/list/";
    public static final String API_KEY_DELETE = API_U + "key/delete/";
    public static final String API_KEY_UPDATE = API_U + "key/update";
}
