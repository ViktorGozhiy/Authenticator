package com.github.viktorgozhiy.authenticator.service.impl;

import com.github.viktorgozhiy.authenticator.dto.KeyDTO;
import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.repository.KeyRepository;
import com.github.viktorgozhiy.authenticator.service.iface.KeyService;
import com.github.viktorgozhiy.authenticator.service.iface.UserService;
import com.github.viktorgozhiy.authenticator.support.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    private UserService userService;

    @Autowired
    private KeyRepository keyRepository;

    @Override
    public List<KeyDTO> getAllKeysByUser(User user) throws RestException {
        return null;
    }

    @Override
    public long updateKey(KeyDTO keyDTO, User user) throws RestException {
        return 0;
    }

    @Override
    public void deleteKey(Long keyId, User user) throws RestException {

    }
}
