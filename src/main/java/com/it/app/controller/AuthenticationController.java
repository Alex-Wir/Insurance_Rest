package com.it.app.controller;

import com.it.app.dto.request.AuthenticationRequestDto;
import com.it.app.dto.request.UserRegistrationRequestDto;
import com.it.app.dto.response.TokenResponseDto;
import com.it.app.dto.response.UserResponseDto;
import com.it.app.model.Role;
import com.it.app.model.User;
import com.it.app.service.RoleService;
import com.it.app.service.UserService;
import com.it.app.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Authentication controller
 */
@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final Mapper mapper;

    /**
     * Verify user
     *
     * @param authenticationRequestDto - Request for sign in
     * @return - TokenResponseDto
     */
    @PostMapping("/signIn")
    public TokenResponseDto authenticateUser(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenResponseDto(tokenService.generate(authentication));
    }

    /**
     * Refresh existing token
     *
     * @param token - existing String Token
     * @return - TokenResponseDto
     */
    @PostMapping("/refresh")
    public TokenResponseDto refreshToken(@RequestParam String token) {
        return new TokenResponseDto(tokenService.refresh(token));
    }

    /**
     * Create new User
     *
     * @param userRegistrationRequestDto - new User's parameters
     * @return - UserResponseDto
     */
    @PostMapping("/signUp")
    public UserResponseDto registerUser(@Valid @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        final User user = new User();
        user.setName(userRegistrationRequestDto.getUsername());
        user.setPassword(encoder.encode(userRegistrationRequestDto.getPassword()));
        final Set<Role> roles = userRegistrationRequestDto.getRoles().stream()
                .map(roleService::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        final UserResponseDto userResponseDto = mapper.map(userService.save(user), UserResponseDto.class);
        return userResponseDto;
    }
}
