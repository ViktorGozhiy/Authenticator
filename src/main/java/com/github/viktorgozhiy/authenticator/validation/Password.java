package com.github.viktorgozhiy.authenticator.validation;

import com.github.viktorgozhiy.authenticator.support.MESSAGES;

import javax.persistence.Table;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default MESSAGES.ERR_PASSWORD_INVALID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
