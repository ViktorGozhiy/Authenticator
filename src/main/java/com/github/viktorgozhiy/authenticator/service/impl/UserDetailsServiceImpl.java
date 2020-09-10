package com.github.viktorgozhiy.authenticator.service.impl;

import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.repository.UserRepository;
import com.github.viktorgozhiy.authenticator.service.iface.UserService;
import com.github.viktorgozhiy.authenticator.support.RestException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, ApplicationContextAware {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = findUser(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return user;
    }

    private User findUser(final String username) {
        if (userRepository != null) return userRepository.findUserByUsername(username);
        if (!context.containsBean("userRepository"))
            throw new UnsupportedOperationException("Can't find user, because userRepository not exists!");
        userRepository = context.getBean(UserRepository.class);
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
