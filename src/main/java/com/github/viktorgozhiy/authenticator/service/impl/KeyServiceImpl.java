package com.github.viktorgozhiy.authenticator.service.impl;

import com.github.viktorgozhiy.authenticator.dto.KeyDTO;
import com.github.viktorgozhiy.authenticator.entity.Key;
import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.repository.KeyRepository;
import com.github.viktorgozhiy.authenticator.service.iface.ErrorResponseFactory;
import com.github.viktorgozhiy.authenticator.service.iface.KeyService;
import com.github.viktorgozhiy.authenticator.service.iface.UserService;
import com.github.viktorgozhiy.authenticator.support.ApiStatus;
import com.github.viktorgozhiy.authenticator.support.MESSAGES;
import com.github.viktorgozhiy.authenticator.support.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KeyServiceImpl implements KeyService {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    @Override
    public List<KeyDTO> getAllKeysByUser(User user) throws RestException {
        List<Key> keyList = keyRepository.findAllByUser(user);
        return keyList.stream().map(KeyDTO::new).collect(Collectors.toList());
    }

    @Override
    public long updateKey(KeyDTO keyDTO, User user) throws RestException {
        if (keyDTO.getId() <= 0) { // Create new
            Key key = new Key(keyDTO, user);
            key = keyRepository.save(key);
            return key.getId();
        } else { // Update exists one
            Key key = keyRepository.getOne(keyDTO.getId());
            if (key == null || !key.getUser().equals(user)) throw new RestException(errorResponseFactory.getErrorResponse(
                    MESSAGES.ERR_KEY_NOT_FOUND, ApiStatus.KEY_NOT_FOUND), HttpStatus.NOT_FOUND);
            key.update(keyDTO);
            key = keyRepository.save(key);
            return key.getId();
        }
    }

    @Override
    public void deleteKey(Long keyId, User user) throws RestException {
        Key key = keyRepository.getOne(keyId);
        if (key == null || !key.getUser().equals(user)) throw new RestException(errorResponseFactory.getErrorResponse(
                MESSAGES.ERR_KEY_NOT_FOUND, ApiStatus.KEY_NOT_FOUND), HttpStatus.NOT_FOUND);
        keyRepository.delete(key);
    }
}
