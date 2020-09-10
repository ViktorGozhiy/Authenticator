package com.github.viktorgozhiy.authenticator.repository;

import com.github.viktorgozhiy.authenticator.entity.Key;
import com.github.viktorgozhiy.authenticator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {

    List<Key> findAllByUser(User user);
}
