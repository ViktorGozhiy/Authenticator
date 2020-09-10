package com.github.viktorgozhiy.authenticator.service.iface;

import com.github.viktorgozhiy.authenticator.dto.UserDTO;
import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.support.RestException;

public interface UserService {
    void createUser(UserDTO userDTO) throws RestException;
    void confirmEmail(String email, String code) throws RestException;
    void recoveryPassword(String email) throws RestException;
    User checkUserConfirmed(String username) throws RestException;
    void sendCode(String username) throws RestException;
}
