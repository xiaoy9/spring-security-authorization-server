package com.xiao9.user.infrastruction.security.login;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if ("/login/mobile".equalsIgnoreCase(request.getRequestURI())
                &&  "post".equalsIgnoreCase(request.getMethod())) {
                validateSmsCode(new ServletWebRequest(request));
        }
        filterChain.doFilter(request, response);
    }

    private void validateSmsCode(ServletWebRequest servletWebRequest)  {
        System.out.println("当前验证一定通过");
    }
}
