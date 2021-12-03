package com.xiao9.user.infrastructure.security.error;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户未激活错误信息
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
