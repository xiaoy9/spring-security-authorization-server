package com.xiao9.user.infrastruction.security.expansion.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {


    @Resource
    private UserDetailsService userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();

        String trueCode = "OK";
        if(StringUtils.isEmpty(trueCode) || !trueCode.equals(code)) {
            throw new RuntimeException("手机验证码不正确！");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername("yy");
        SmsCodeAuthenticationToken result = new SmsCodeAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        result.setDetails(authentication.getDetails());
        result.setAuthenticated(true);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
