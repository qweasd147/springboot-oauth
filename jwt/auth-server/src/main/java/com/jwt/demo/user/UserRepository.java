package com.jwt.demo.user;

import com.jwt.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdx(Long idx);
    User findByProviderAndId(String provider, Long idx);
}
