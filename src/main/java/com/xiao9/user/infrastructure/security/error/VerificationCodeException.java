package com.xiao9.user.infrastructure.security.error;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证校验不通过信息
 */
public class VerificationCodeException extends AuthenticationException {

    private static final long serialVersionUID = 2L;

    public VerificationCodeException(String msg) {
        super(msg);
    }
}
