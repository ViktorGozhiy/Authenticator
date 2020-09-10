package com.github.viktorgozhiy.authenticator.controller;

import com.github.viktorgozhiy.authenticator.dto.KeyDTO;
import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.service.iface.KeyService;
import com.github.viktorgozhiy.authenticator.service.iface.UserService;
import com.github.viktorgozhiy.authenticator.support.API;
import com.github.viktorgozhiy.authenticator.support.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class KeyController {

    @Autowired
    private UserService userService;

    @Autowired
    private KeyService keyService;

    @GetMapping(API.API_KEY_LIST)
    @ResponseStatus(HttpStatus.OK)
    private List<KeyDTO> getKeyList(Principal principal) throws RestException {
        final User user = userService.checkUserConfirmed(principal.getName());
        return keyService.getAllKeysByUser(user);
    }

    @PostMapping(API.API_KEY_UPDATE)
    @ResponseStatus(HttpStatus.CREATED)
    private long updateKey(@RequestBody @Valid KeyDTO keyDTO, Principal principal) throws RestException {
        final User user = userService.checkUserConfirmed(principal.getName());
        return keyService.updateKey(keyDTO, user);
    }

    @PostMapping(API.API_KEY_DELETE)
    @ResponseStatus(HttpStatus.OK)
    private void deleteKey(@RequestBody Long keyId, Principal principal) throws RestException {
        final User user = userService.checkUserConfirmed(principal.getName());
        keyService.deleteKey(keyId, user);
    }
}
