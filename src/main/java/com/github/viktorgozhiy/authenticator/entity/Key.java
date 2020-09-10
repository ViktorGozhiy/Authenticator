package com.github.viktorgozhiy.authenticator.entity;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "otp_key")
public class Key {

    private static final String ENC_KEY = "mj9TE8qsjg2k";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "secret_key", length = 512, nullable = false)
    @ColumnTransformer(read = "AES_DECRYPT(UNHEX(secret_key)), '" + ENC_KEY + "')", write = "HEX(AES_ENCRYPT(?, '" + ENC_KEY + "'))")
    private String secretKey;

    @Column(name = "timeout")
    private long timeout;

    @Column(name = "algorithm", length = 50)
    private String algorithm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return timeout == key.timeout &&
                Objects.equals(label, key.label) &&
                secretKey.equals(key.secretKey) &&
                Objects.equals(algorithm, key.algorithm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, secretKey, timeout, algorithm);
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", timeout=" + timeout +
                ", algorithm='" + algorithm + '\'' +
                '}';
    }
}
