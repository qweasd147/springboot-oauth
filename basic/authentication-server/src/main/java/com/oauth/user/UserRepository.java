package com.oauth.user;

import com.oauth.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdx(Long idx);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String userId);
}
