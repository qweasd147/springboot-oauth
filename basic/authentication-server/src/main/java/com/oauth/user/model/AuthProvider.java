package com.oauth.user.model;

import com.oauth.user.convert.EnumCodeType;
import lombok.AllArgsConstructor;

/**
 * 인증 정보를 제공해주는곳 구분 정보
 */
@AllArgsConstructor
public enum AuthProvider implements EnumCodeType {

    LOCAL(0, "자체 인증")
    , KAKAO(1, "by kakao")
    , GOOGLE(2, "by google")
    , NAVER(3, "by naver");

    private int code;
    private String desc;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
