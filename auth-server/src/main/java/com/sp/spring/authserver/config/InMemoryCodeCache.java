package com.sp.spring.authserver.config;

import java.util.concurrent.TimeUnit;

import com.sp.spring.authserver.web.UserAuthentication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(PkceConfigurationProperties.class)
public class InMemoryCodeCache {

    private final PkceConfigurationProperties pkceConfigurationProperties;

    @Bean
    public LoadingCache<String, UserAuthentication> loadingCache() {
        final var expirationMinutes = pkceConfigurationProperties.getSecurity().getCodeExpirationMinutes();
        return CacheBuilder.newBuilder().expireAfterWrite(expirationMinutes, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    public UserAuthentication load(String code) {
                        return UserAuthentication.builder().build();
                    }
                });
    }



}
