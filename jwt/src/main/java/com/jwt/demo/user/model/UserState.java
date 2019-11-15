package com.jwt.demo.user.model;


import com.jwt.demo.common.state.EnumCodeType;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;


@AllArgsConstructor
public enum UserState implements EnumCodeType {
    ENABLE(0, "사용가능")
    , DELETE(1, "삭제")
    , LOCKED(2, "잠금")
    , EXPIRED(3, "만료");

    private int state;
    private String description;


    @Override
    public int getCode() {
        return this.state;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public int getState(){
        return  this.state;
    }

    public static UserState findStateByCode(int findState) {

        return Arrays.stream(UserState.values())
                .filter(userState -> userState.getState() == findState)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("알 수 없는 state code. code : "+findState));
    }
}
