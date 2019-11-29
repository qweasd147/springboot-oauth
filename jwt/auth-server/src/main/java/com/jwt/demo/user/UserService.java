package com.jwt.demo.user;

import com.jwt.demo.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.jwt.demo.user.model.UserDto.*;

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
}
