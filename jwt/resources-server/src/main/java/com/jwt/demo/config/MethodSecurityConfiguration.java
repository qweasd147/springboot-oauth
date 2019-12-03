package com.jwt.demo.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfiguration {

}
