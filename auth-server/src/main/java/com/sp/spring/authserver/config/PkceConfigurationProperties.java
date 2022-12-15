package com.sp.spring.authserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.sp.spring")
@Data
public class PkceConfigurationProperties {

    private Security security = new Security();

    @Data
    public class Security {

        private String clientId;
        private String redirectUri;
        private String responseType;
        private String codeChallengeMethod;
        private String grantType;
        private Integer codeExpirationMinutes;
        private Refresh refresh = new Refresh();

        @Data
        public class Refresh {
            private String grantType;
        }

    }
}
