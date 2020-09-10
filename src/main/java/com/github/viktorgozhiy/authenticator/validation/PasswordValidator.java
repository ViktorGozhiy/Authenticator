package com.github.viktorgozhiy.authenticator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    public static final String PASSWORD_PATTERN = "[_A-Za-z0-9]{8,42}";

    private Pattern pattern;

    @Override
    public void initialize(Password constraintAnnotation) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
