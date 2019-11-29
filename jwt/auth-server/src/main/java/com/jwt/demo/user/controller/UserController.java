package com.jwt.demo.user.controller;

import com.jwt.demo.user.UserService;
import com.jwt.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.jwt.demo.user.model.UserDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public Principal me(final Principal principal){
        return principal;
    }

    @PostMapping("/signup")
    public Res signUp(final SignUpRequestDto signUpRequestDto){

        User createdUser = userService.signUp(signUpRequestDto);

        return Res.of(createdUser);
    }
}
