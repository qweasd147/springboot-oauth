package com.oauth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Deprecated
@RequiredArgsConstructor
public class CustomLoginAuthProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        if (null == userDetails) {
            throw new UsernameNotFoundException("user 찾을 수 없음 user : " + username);
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("user 잠금 처리됨 user : " + username);
        }

        /*
        if (!encoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException();
        }
        */

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
