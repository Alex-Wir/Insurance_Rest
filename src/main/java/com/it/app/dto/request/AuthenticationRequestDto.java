package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO class for User sign in
 */
@Getter
@Setter
public class AuthenticationRequestDto {

    private String username;
    private String password;
}
