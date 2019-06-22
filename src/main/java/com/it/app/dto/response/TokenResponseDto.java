package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO class with access Token
 */
@Getter
@Setter
public class TokenResponseDto {

    private String token;
    private String type = "Bearer";

    public TokenResponseDto(String accessToken) {
        this.token = accessToken;
    }
}
