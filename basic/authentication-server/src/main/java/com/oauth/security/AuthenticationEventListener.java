package com.oauth.security;

import com.oauth.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEventListener implements AuthenticationEventPublisher {

    private final UserService userService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {


        log.info("success authentication");

        //String userIdx = authentication.getName();
        //userService.resetFailCount(Long.valueOf(userIdx));
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

        String email = authentication.getName();

        userService.failLogin(email);
        //throw new LockedException("계정 잠김");
    }
}
