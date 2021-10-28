package com.xiao9.user.infrastruction.security.error;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {

    private static final long serialVersionUID = 2L;

    public VerificationCodeException(String msg) {
        super(msg);
    }
}
