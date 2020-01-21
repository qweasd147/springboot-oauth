package com.oauth.user;

import com.oauth.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.oauth.user.model.UserDto.*;


@Service
@AllArgsConstructor
@Transactional
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
}
