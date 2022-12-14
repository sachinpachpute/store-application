package com.sp.spring.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.spring.commons.exception.GenericBadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
@Slf4j
@Component
@AllArgsConstructor
public class RequestReader {

    private final ObjectMapper objectMapper;

    public String readGrantType(final String requestBody) {
        HashMap<String, String> request;
        try {
            request = objectMapper.readValue(requestBody, new TypeReference<HashMap<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Grant type not present in request: ", e);
            throw new GenericBadRequestException("Request Body not properly constructed, No grantType found");
        }
        return request.get("grantType");
    }

    public boolean isValidRedirectUri(final String redirectUri, final String commaSeperatedRedirectUri) {
        boolean result = false;
        final var redirectUriList = Arrays.asList(commaSeperatedRedirectUri.split(","));
        for (int i = 0; i < redirectUriList.size(); i++) {
            if (redirectUriList.get(i).trim().equals(redirectUri))
                result = true;
        }
        return result;
    }

}
