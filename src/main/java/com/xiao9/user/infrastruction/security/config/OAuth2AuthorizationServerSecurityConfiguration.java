package com.xiao9.user.infrastruction.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationServerSecurityConfiguration extends AuthorizationServerConfigurerAdapter {

    public static final String CLIENT_ID = "reader";
    public static final String SECRET = "{noop}secret";


    AuthenticationManager authenticationManager;

    public OAuth2AuthorizationServerSecurityConfiguration(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient(CLIENT_ID)
                .authorizedGrantTypes("password")
                .secret(SECRET)
                .scopes("message:read")
                .accessTokenValiditySeconds(600_000_000);
        // @formatter:on
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // @formatter:off
        endpoints.authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore());
        endpoints.accessTokenConverter(accessTokenConverter());
        // @formatter:on
    }



    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("asjgiougjoiiogajsoiuiuwphoywwierghhj1243asdf235vabby43d2t4x34tf345g62");
        return converter;
    }

}
