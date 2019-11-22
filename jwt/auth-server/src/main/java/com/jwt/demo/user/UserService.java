package com.jwt.demo.user;

import com.jwt.demo.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByIdx(Long userIdx){
        return userRepository.findByIdx(userIdx);
    }
}
