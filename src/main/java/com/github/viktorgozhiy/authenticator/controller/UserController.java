package com.github.viktorgozhiy.authenticator.controller;

import com.github.viktorgozhiy.authenticator.dto.ConfirmationCodeDTO;
import com.github.viktorgozhiy.authenticator.dto.UserDTO;
import com.github.viktorgozhiy.authenticator.service.iface.UserService;
import com.github.viktorgozhiy.authenticator.support.API;
import com.github.viktorgozhiy.authenticator.support.MESSAGES;
import com.github.viktorgozhiy.authenticator.support.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(API.API_SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserDTO userDTO) throws RestException {
        userService.createUser(userDTO);
    }

    @PostMapping(API.API_CONFIRM)
    @ResponseStatus(HttpStatus.OK)
    public void confirmEmail(@Valid @RequestBody ConfirmationCodeDTO confirmationCodeDTO) throws RestException {
        userService.confirmEmail(confirmationCodeDTO.getUsername(), confirmationCodeDTO.getCode());
    }

    @PostMapping(API.API_SEND_CODE)
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailConfirmationCodeAgain(@RequestBody String username) throws RestException {
        userService.sendCode(username);
    }

    @PostMapping(API.API_RECOVERY)
    @ResponseStatus(HttpStatus.OK)
    public void sendNewPasswordToEmail(@RequestBody String username) throws RestException {
        userService.recoveryPassword(username);
    }
}
