package com.oauth.api;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/me")
    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("#oauth2.hasScope('read_profile')")
    public Principal me(final Principal principal){
        return principal;
    }


    @GetMapping
    public ResponseEntity ok(){
        return ResponseEntity.ok().build();
    }
}
