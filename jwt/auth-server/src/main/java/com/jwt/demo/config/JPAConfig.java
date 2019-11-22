package com.jwt.demo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Slf4j
public class JPAConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public AuditorAware auditorAware(){
        return () -> {
            //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
            //return Optional.of(user.getNickName());
            return Optional.of("temp_id");
        };
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}