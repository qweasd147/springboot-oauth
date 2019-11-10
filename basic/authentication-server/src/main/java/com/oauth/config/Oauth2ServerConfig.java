package com.oauth.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableAuthorizationServer
@Slf4j
@RequiredArgsConstructor
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthorizationCodeServices authorizationCodeServices;
    private final ApprovalStore approvalStore;
    private final TokenStore tokenStore;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .requestValidator(new AuthRequestValidator())
                .tokenStore(tokenStore)
                .authorizationCodeServices(authorizationCodeServices)
                .approvalStore(approvalStore);
    }
}
