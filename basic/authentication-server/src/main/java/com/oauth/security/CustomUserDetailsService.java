package com.oauth.security;

import com.oauth.user.UserService;
import com.oauth.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userService.findByEmail(email);

        if(!user.isPresent()){
            log.warn("사용자를 찾을수 없음 : " + email);
            throw new UsernameNotFoundException("사용자 없음! " + email);
        }

        CustomUserDetails userDetails = new CustomUserDetails(user.get());

        //TODO : 해당 사용자의 권한 정보 입력
        userDetails.setAuthorities(Collections.EMPTY_SET);

        return userDetails;
    }
}
