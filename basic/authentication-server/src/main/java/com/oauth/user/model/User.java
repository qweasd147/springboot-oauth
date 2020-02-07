package com.oauth.user.model;

import com.oauth.user.convert.AuthProviderConverter;
import com.oauth.user.convert.UserStateConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String password;

    @Column(nullable = false)
    @Convert(converter = AuthProviderConverter.class)
    @NotNull
    private AuthProvider provider = AuthProvider.LOCAL;		//self, kakao, naver 등

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

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private Integer loginTryCount = 0;

    @Builder
    public User(String password, @NotNull AuthProvider provider, @NotNull String id
            , @NotNull String name, @NotNull String nickName, @NotNull String email
            , String thirdPartyToken, UserState state, String createdBy, String lastModifiedBy) {
        this.password = password;
        this.provider = provider;
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.thirdPartyToken = thirdPartyToken;
        this.state = state;
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = LocalDateTime.now();
        this.loginTryCount = 0;
    }

    protected void cloneUser(User user){

        this.idx = user.getIdx();
        this.provider = user.getProvider();
        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.thirdPartyToken = user.getThirdPartyToken();
        this.state = user.getState();
        this.loginTryCount = user.getLoginTryCount();
    }

    public void incrementFailCount(){
        this.loginTryCount++;
    }

    public void lockIfOverTryCount(){

        if(this.loginTryCount < 5)  return;

        this.state = UserState.LOCKED;
    }

    public void resetTryCount(){
        this.loginTryCount = 0;
    }
}
