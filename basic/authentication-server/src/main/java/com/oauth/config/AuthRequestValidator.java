package com.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenRequest;

@Slf4j
public class AuthRequestValidator implements OAuth2RequestValidator {

    @Override
    public void validateScope(TokenRequest tokenRequest, ClientDetails client) throws InvalidScopeException {
        log.debug("validate TokenRequest : {}", tokenRequest);
        log.debug("validate client : {}", client);
    }

    @Override
    public void validateScope(AuthorizationRequest authorizationRequest, ClientDetails client)
            throws InvalidScopeException {

        log.debug("\n## validate authorizationRequest : {}", authorizationRequest);
        log.debug("\n## client : {}", client);
    }
}
