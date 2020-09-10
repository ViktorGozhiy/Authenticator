package com.github.viktorgozhiy.authenticator.service.iface;

import com.github.viktorgozhiy.authenticator.dto.KeyDTO;
import com.github.viktorgozhiy.authenticator.entity.Key;
import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.support.RestException;

import java.util.List;

public interface KeyService {

    List<KeyDTO> getAllKeysByUser(User user) throws RestException;
    long updateKey(KeyDTO keyDTO, User user) throws RestException;
    void deleteKey(Long keyId, User user) throws RestException;
}
