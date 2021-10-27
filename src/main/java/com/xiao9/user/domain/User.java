package com.xiao9.user.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class User {

    // 用户id
    private Long id;

    // 登录名
    private String login;

    // 密码
    private String password;

    // 邮箱
    private String email;

    // 昵称
    private String nickname;

    // 激活标志
    private boolean activated = false;

    // 用户头像
    private String imageUrl;

    // 用户角色
    private Set<Role> roles;

    // 用户激活码
    private String activationKey;

    // 密码重置码
    private String resetKey;

    // 重置日期
    private LocalDateTime resetDate = null;

    // 创建日期
    private LocalDateTime createdDate;



    public void register() {

    }
}
