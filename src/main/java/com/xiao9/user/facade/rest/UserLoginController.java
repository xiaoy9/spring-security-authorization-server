package com.xiao9.user.facade.rest;

import com.xiao9.user.application.service.LoginService;
import com.xiao9.user.facade.rest.view.LoginDTO;
import com.xiao9.user.infrastruction.security.jwt.JWTFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    private final LoginService loginService;

    public UserLoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    /**
     * 用户通过用户名密码登录获取jwt
     * @param login 登录名或者邮箱
     * @return jwtToken
     */
    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@RequestBody LoginDTO login) {
        String jwt = loginService.getJWTToken(login);
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWTFilter.AUTHORIZATION_HEADER, jwt);
        return new ResponseEntity<>(new JWTToken(jwt), headers, HttpStatus.OK);
    }


}
