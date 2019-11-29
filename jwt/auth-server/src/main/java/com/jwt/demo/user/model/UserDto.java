package com.jwt.demo.user.model;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@RequiredArgsConstructor
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
                    .id("local")
                    .name(this.name)
                    .nickName(this.nickName)
                    .provider(AuthProvider.SELF)
                    .state(UserState.ENABLE)
                    .password(passwordEncoder.encode(this.password))
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
