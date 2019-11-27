package com.jwt.demo.security;

import com.jwt.demo.user.model.User;
import com.jwt.demo.user.model.UserState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = -6180636340827094522L;

    private Set<? extends GrantedAuthority> authorities;

    public CustomUserDetails() {
        super();
    }
    public CustomUserDetails(User user) {
        initByUser(user);

    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        //throw new UnsupportedOperationException("내가 직접 비밀번호 관리안함");
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(getIdx());
    }

    @Override
    public boolean isAccountNonExpired() {
        return getState() != UserState.EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getState() == UserState.ENABLE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getState() != UserState.EXPIRED;
    }

    @Override
    public boolean isEnabled() {
        return getState() == UserState.ENABLE;
    }
}
