package com.oauth.user.convert;

import com.oauth.user.model.UserState;

public class UserStateConverter extends AbstractEnumConverter<UserState> {

    public UserStateConverter() {
        this.enumCodeType = UserState.class;
    }
}
