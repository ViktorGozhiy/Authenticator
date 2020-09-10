package com.github.viktorgozhiy.authenticator.dto;

import com.github.viktorgozhiy.authenticator.support.MESSAGES;
import com.github.viktorgozhiy.authenticator.validation.Password;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserDTO implements Serializable {

    @Email(message = MESSAGES.ERR_EMAIL_INVALID)
    @NotBlank(message = MESSAGES.ERR_USERNAME_NOT_BLANK)
    private String username;

    @Password
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
