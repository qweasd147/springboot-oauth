package com.jwt.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@ConfigurationProperties("security")
@Getter
@Setter
public class SecurityProperties {

    private JwtProperties jwt;

    @Setter
    public static class JwtProperties {

        private Resource publicKey;

        /**
         * public key를 가져온다.
         *
         * 파일 내용이 얼마나 많을지 모르니까 그냥 그때그때 읽어서 반환
         * @return
         */
        public String getPublicKey() {

            try(InputStream is = this.publicKey.getInputStream()){

                byte[] byteData = FileCopyUtils.copyToByteArray(is);
                return new String(byteData, StandardCharsets.UTF_8);

            } catch (IOException e) {
                throw new RuntimeException("파일 읽기 실패",e.getCause());
            }
        }
    }
}
