package com.sp.spring.authserver.security.utility;

import com.sp.spring.authserver.web.CodeExchangeRequest;
import com.sp.spring.authserver.web.RefreshTokenRequest;
import com.sp.spring.commons.exception.GenericBadRequestException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ObjectConverter {

    private final ObjectMapper objectMapper;

    public CodeExchangeRequest convert(final String codeExchangeRequest,
                                       final Class<CodeExchangeRequest> dtoClass) {
        try {
            return objectMapper.readValue(codeExchangeRequest, CodeExchangeRequest.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse JSON to object: ", e);
            throw new GenericBadRequestException("Request Body not properly constructed");
        }
    }

    public RefreshTokenRequest convertRefresh(final String refreshTokenRequest,
                                              final Class<RefreshTokenRequest> dtoClass) {
        try {
            return objectMapper.readValue(refreshTokenRequest, RefreshTokenRequest.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse JSON to object: ", e);
            throw new GenericBadRequestException("Request Body not properly constructed");
        }
    }

}
