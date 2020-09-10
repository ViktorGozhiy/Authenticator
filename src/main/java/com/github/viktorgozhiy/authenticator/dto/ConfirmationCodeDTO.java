package com.github.viktorgozhiy.authenticator.dto;

import com.github.viktorgozhiy.authenticator.support.MESSAGES;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ConfirmationCodeDTO implements Serializable {

    @Email(message = MESSAGES.ERR_EMAIL_INVALID)
    @NotBlank(message = MESSAGES.ERR_USERNAME_NOT_BLANK)
    private String username;

    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
