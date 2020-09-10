package com.github.viktorgozhiy.authenticator.service.impl;

import com.github.viktorgozhiy.authenticator.dto.UserDTO;
import com.github.viktorgozhiy.authenticator.entity.Role;
import com.github.viktorgozhiy.authenticator.entity.User;
import com.github.viktorgozhiy.authenticator.repository.RoleRepository;
import com.github.viktorgozhiy.authenticator.repository.UserRepository;
import com.github.viktorgozhiy.authenticator.service.iface.ErrorResponseFactory;
import com.github.viktorgozhiy.authenticator.service.iface.UserService;
import com.github.viktorgozhiy.authenticator.support.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Locale;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageSource messageSource;


    @Override
    public void createUser(UserDTO userDTO) throws RestException {
        String code = getRandomCode();
        checkUserUnique(userDTO.getUsername());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.getAuthorities().add(roleRepository.findRoleByAuthority(Role.ROLE_USER));
        user.setEmailConfirmationCode(code);
        user = userRepository.save(user);
        //sendCode(user); // TODO Uncomment on release
    }

    @Override
    public void confirmEmail(String email, String code) throws RestException {
        User user = getUserByUsername(email);
        if (user.isEmailConfirmed()) {
            throw new RestException(errorResponseFactory.getErrorResponse(
                    MESSAGES.ERR_USER_EMAIL_ALREADY_CONFIRMED,
                    ApiStatus.USER_EMAIL_ALREADY_CONFIRMED), HttpStatus.BAD_REQUEST);
        }
        if (code == null || !user.getEmailConfirmationCode().equals(code.toLowerCase())) {
            throw new RestException(errorResponseFactory.getErrorResponse(
                    MESSAGES.ERR_USER_WRONG_EMAIL_CONFIRMATION_CODE,
                    ApiStatus.WRONG_EMAIL_CONFIRMATION_CODE), HttpStatus.BAD_REQUEST);
        }
        user.setEmailConfirmed(true);
    }

    @Override
    public void sendCode(String username) throws RestException {
        final User user = getUserByUsername(username);
        if (user.isEmailConfirmed()) throw new RestException(errorResponseFactory.getErrorResponse(
                MESSAGES.ERR_USER_EMAIL_ALREADY_CONFIRMED,
                ApiStatus.USER_EMAIL_ALREADY_CONFIRMED), HttpStatus.BAD_REQUEST);
        user.setEmailConfirmationCode(getRandomCode());
        sendCode(user);
        userRepository.save(user);
    }

    private void sendCode(final User user) throws RestException {
        String confirmationCode = " <b>" + user.getEmailConfirmationCode() + "</b>";
        Locale locale = LocaleContextHolder.getLocale();
        mailSender.sendMessage(user.getUsername(),
                messageSource.getMessage(MESSAGES.MESS_CONFIRMATION_CODE_SUBJECT, null, locale),
                messageSource.getMessage(MESSAGES.MESS_CONFIRMATION_CODE_PREFIX + confirmationCode, null, locale));
    }

    @Override
    public void recoveryPassword(String email) throws RestException {
        User user = getUserByUsername(email);
        Locale locale = LocaleContextHolder.getLocale();
        String newPassword = getRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        // TODO Uncomment in release
//        mailSender.sendMessage(user.getUsername(),
//                messageSource.getMessage(MESSAGES.MESS_PASSWORD_RECOVERY_SUBJECT, null, locale),
//                messageSource.getMessage(MESSAGES.MESS_CONFIRMATION_CODE_PREFIX + newPassword, null, locale));
    }

    @Override
    public User checkUserConfirmed(String username) throws RestException {
        final User u = getUserByUsername(username);
        if (!u.isEmailConfirmed()) {
            throw new RestException(errorResponseFactory.getErrorResponse(
                    MESSAGES.ERR_USER_EMAIL_NOT_CONFIRMED,
                    ApiStatus.USER_EMAIL_ALREADY_CONFIRMED
            ), HttpStatus.FORBIDDEN);
        }
        if (!u.isEnabled()) {
            throw new RestException(errorResponseFactory.getErrorResponse(
                    MESSAGES.ERR_USER_NOT_ACCEPTED,
                    ApiStatus.USER_NOT_ACCEPTED
            ), HttpStatus.FORBIDDEN);
        }
        return u;
    }

    private User getUserByUsername(String username) throws RestException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new RestException(errorResponseFactory.getErrorResponse(
                    MESSAGES.ERR_USER_NOT_FOUND,
                    ApiStatus.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return user;
    }

    private String getRandomCode() {
        return RandomStringUtils.random(6, true, false).toLowerCase();
    }

    private String getRandomPassword() {
        return RandomStringUtils.random(8, true, true);
    }

    private void checkUserUnique(String email) throws RestException {
        User user = userRepository.findUserByUsername(email);
        if (user == null) return;
        throw new RestException(errorResponseFactory.getErrorResponse(
                MESSAGES.ERR_USER_ALREADY_EXISTS,
                ApiStatus.USER_ALREADY_EXISTS), HttpStatus.CONFLICT);
    }
}
