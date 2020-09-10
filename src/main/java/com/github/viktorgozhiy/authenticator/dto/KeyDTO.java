package com.github.viktorgozhiy.authenticator.dto;

import com.github.viktorgozhiy.authenticator.entity.Key;
import com.github.viktorgozhiy.authenticator.support.MESSAGES;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class KeyDTO implements Serializable {

    private long id;

    @NotBlank(message = MESSAGES.ERR_KEY_LABEL_NOT_BLANK)
    private String label;

    @NotBlank(message = MESSAGES.ERR_KEY_SECRET_KEY_NOT_BLANK)
    private String secretKey;

    private long timeout;

    @NotBlank(message = MESSAGES.ERR_KEY_ALGORITHM_NOT_BLANK)
    private String algorithm;

    public KeyDTO() {}

    public KeyDTO(Key key) {
        this.id = key.getId();
        this.label = key.getLabel();
        this.secretKey = key.getSecretKey();
        this.timeout = key.getTimeout();
        this.algorithm = key.getAlgorithm();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
