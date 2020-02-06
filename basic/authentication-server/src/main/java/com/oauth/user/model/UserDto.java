package com.oauth.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpRequestDto {

        @NotBlank(message = "이메일 필수 입력")
        private String email;
        @NotBlank(message = "이름 필수 입력")
        private String name;
        @NotBlank(message = "닉네임 필수 입력")
        private String nickName;
        @NotBlank(message = "비밀번호 필수 입력")
        private String password;

        @Builder
        private SignUpRequestDto(String email, String name, String nickName, String password) {
            this.email = email;
            this.name = name;
            this.nickName = nickName;
            this.password = password;
        }

        public User toEntity(PasswordEncoder passwordEncoder){

            return User.builder()
                    .email(this.email)
                    .id("tempId")
                    .name(this.name)
                    .nickName(this.nickName)
                    .provider(AuthProvider.LOCAL)
                    .state(UserState.ENABLE)
                    .password(passwordEncoder.encode(this.password))
                    .createdBy("temp")
                    .lastModifiedBy("temp")
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {

        private Long idx;
        private String email;
        private String name;
        private String nickName;

        @Builder
        private Res(Long idx, String email, String name, String nickName) {
            this.idx = idx;
            this.email = email;
            this.name = name;
            this.nickName = nickName;
        }

        public static Res of(User user){

            return Res.builder()
                    .idx(user.getIdx())
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .build();
        }
    }
}
