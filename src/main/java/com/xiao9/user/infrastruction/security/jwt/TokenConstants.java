package com.xiao9.user.infrastruction.security.jwt;

public class TokenConstants {

    // SECRET 已经是被base64编码后的
    public static final String SECRET = "a5dI/megKQnCk5leHwBLbno0IvNjJSVmZeJ2K9F1/tS6kmAy3ACibdP+73klNvrvZPTCkuRykZd+IXXlTYKr5Q==";

    // 令牌过期时间 24h
    public static final long TOKEN_VALIDITY_IN_SECONDS = 86400;

    // 选择记住我后，令牌过期时间为30天
    public static final long TOKEN_VALIDITY_IN_SECONDS_FOR_REMEMBER_ME = 2592000;
}
