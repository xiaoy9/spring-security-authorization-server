package com.xiao9.user.facade.rest.view;

import lombok.Data;

@Data
public class LoginDTO {

    // 登录名
    private String username;

    // 密码
    private String password;

    // 是否记住
    private Boolean rememberMe;
}
