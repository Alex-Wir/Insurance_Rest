package com.it.app.service.security.impl;

import com.it.app.model.User;
import com.it.app.security.model.AuthenticationUserDetails;
import com.it.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation UserDetailsService interface
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    /**
     * Find user by name and check his granted authority
     *
     * @param username - the username identifying the user whose data is required
     * @return - AuthenticationUserDetails with authorities
     * @throws UsernameNotFoundException - if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByName(username);
        final Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new AuthenticationUserDetails(user.getId(), user.getName(), user.getPassword(), authorities);
    }
}