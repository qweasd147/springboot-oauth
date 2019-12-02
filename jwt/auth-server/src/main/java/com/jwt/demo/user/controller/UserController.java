package com.jwt.demo.user.controller;

import com.jwt.demo.user.UserService;
import com.jwt.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public Res signUp(@Valid final SignUpRequestDto signUpRequestDto){

        User createdUser = userService.signUp(signUpRequestDto);

        return Res.of(createdUser);
    }
}
