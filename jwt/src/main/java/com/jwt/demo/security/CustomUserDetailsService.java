package com.jwt.demo.security;

import com.jwt.demo.user.UserService;
import com.jwt.demo.user.model.User;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.findByIdx(Long.parseLong(username));

        if(!user.isPresent()){
            log.debug("사용자 없음! : "+username);
            throw new UsernameNotFoundException("사용자 없음!");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user.get());

        //TODO : 해당 사용자의 권한 정보 입력
        userDetails.setAuthorities(Collections.EMPTY_SET);

        return userDetails;
    }
}
