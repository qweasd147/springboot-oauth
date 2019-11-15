package com.jwt.demo.user.model;

import com.jwt.demo.common.converter.AbstractEnumConverter;

public class UserStateConverter extends AbstractEnumConverter<UserState> {

    public UserStateConverter() {
        this.enumCodeType = UserState.class;
    }
}
