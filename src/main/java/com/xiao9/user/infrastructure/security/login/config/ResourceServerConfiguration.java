package com.xiao9.user.infrastructure.security.login.config;

import com.xiao9.user.infrastructure.security.UsernameOrEmailAuthDetailService;
import com.xiao9.user.infrastructure.security.login.AfterAuthenticationSuccess;
import com.xiao9.user.infrastructure.security.login.mobile.SmsAuthenticationConfig;
import com.xiao9.user.infrastructure.security.login.mobile.SmsCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final SmsCodeFilter smsCodeFilter;
    private final UsernameOrEmailAuthDetailService usernameOrEmailAuthDetailService;

    public ResourceServerConfiguration(CorsFilter corsFilter, SmsCodeFilter smsCodeFilter,
                                       UsernameOrEmailAuthDetailService usernameOrEmailAuthDetailService) {
        this.corsFilter = corsFilter;
        this.smsCodeFilter = smsCodeFilter;
        this.usernameOrEmailAuthDetailService = usernameOrEmailAuthDetailService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().apply(smsLoginConfig());
    }


    @Bean
    SmsAuthenticationConfig smsLoginConfig() {
        return new SmsAuthenticationConfig(usernameOrEmailAuthDetailService, afterLoginSuccess());
    }


    @Bean
    AuthenticationSuccessHandler afterLoginSuccess() {
        return new AfterAuthenticationSuccess();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(new JwtTokenStore(extractAccessTokenConverter()));
    }

    @Bean
    public JwtAccessTokenConverter extractAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("asjgiougjoiiogajsoiuiuwphoywwierghhj1243asdf235vabby43d2t4x34tf345g62");
        return converter;
    }
}
