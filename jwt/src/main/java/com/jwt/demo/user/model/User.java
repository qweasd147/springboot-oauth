package com.jwt.demo.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    @NotNull
    private String provider;		//default, kakao, naver 등

    /**
     * 각 서드파티에서 어떠한 형태로 제공할지 몰라서 String
     */
    @Column(nullable = false)
    @NotNull
    private String id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String nickName;

    @Column(nullable = false)
    @NotNull
    private String email;

    @Transient
    private String thirdPartyToken;

    @Convert(converter = UserStateConverter.class)
    private UserState state;

    @Embedded
    private WriteInfo<String> writeInfo = new WriteInfo<>();

    @Builder
    private User(String provider, String id, String name, @NotNull String nickName, String email, String thirdPartyToken, UserState state) {

        this.provider = provider;
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.thirdPartyToken = thirdPartyToken;
        this.state = state;
    }

    protected void initByUser(User user){

        this.idx = user.getIdx();
        this.provider = user.getProvider();
        this.id = user.getId();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.thirdPartyToken = user.getThirdPartyToken();
        this.state = user.getState();
    }

    public void initUser(){
        this.state = UserState.ENABLE;
    }
}
