package com.oauth.user;

import com.oauth.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static com.oauth.user.model.UserDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("#oauth2.hasScope('read_profile')")
    public Principal me(final Principal principal){
        return principal;
    }

    @PostMapping("/signup")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Res signUp(@Valid final SignUpRequestDto signUpRequestDto){

        User createdUser = userService.signUp(signUpRequestDto);

        return Res.of(createdUser);
    }
}
