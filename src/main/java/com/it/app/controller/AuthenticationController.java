package com.it.app.controller;

import com.it.app.dto.UserRegistrationRequestDto;
import com.it.app.dto.request.AuthenticationRequestDto;
import com.it.app.dto.response.TokenResponseDto;
import com.it.app.model.Role;
import com.it.app.model.User;
import com.it.app.service.RoleService;
import com.it.app.service.UserService;
import com.it.app.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signIn")
    public TokenResponseDto authenticateUser(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenResponseDto(tokenService.generate(authentication));
    }

    @PostMapping("/refresh")
    public TokenResponseDto refreshToken(@RequestParam String token) {
        return new TokenResponseDto(tokenService.refresh(token));
    }

    @PostMapping("/signUp")
    public User registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        final User user = new User();
        user.setName(userRegistrationRequestDto.getUsername());
        user.setPassword(encoder.encode(userRegistrationRequestDto.getPassword()));
        final Set<Role> roles = userRegistrationRequestDto.getRoles().stream()
                .map(roleService::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return userService.save(user);
    }
}
