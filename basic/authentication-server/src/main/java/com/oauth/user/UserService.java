package com.oauth.user;

import com.oauth.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.oauth.user.model.UserDto.*;


@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByIdx(Long userIdx){
        return userRepository.findByIdx(userIdx);
    }

    public User signUp(final SignUpRequestDto signUpRequestDto){

        User user = signUpRequestDto.toEntity(passwordEncoder);

        return userRepository.save(user);
    }

    public Optional<User> findById(String userId){
        return userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void failLogin(String email){

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(!userOptional.isPresent()){
            log.warn("사용자를 찾을수 없음 : " + email);
            return;
        }

        User user = userOptional.get();

        user.incrementFailCount();
        user.lockIfOverTryCount();
    }

    public void resetFailCount(Long userIdx){

        User user = userRepository.findByIdx(userIdx)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없음! " + userIdx));

        user.resetTryCount();
    }
}
