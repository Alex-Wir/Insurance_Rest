package com.it.app.service.security;

import org.springframework.security.core.Authentication;

/**
 * Token service interface for tokens manage
 */
public interface TokenService {
    String generate(Authentication authentication);

    String refresh(String token);

    String extractUsername(String token);

    boolean validate(String authToken);
}