package com.it.app.config;

import com.it.app.security.filter.AuthenticationTokenFilter;
import com.it.app.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //TODO fix /insurances/users/id for CUSTOMER
    //@PreAuthorize("id == currentUser.getId()") ??
    //@PreAuthorize("#id == authentication.id") ??
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .mvcMatchers("/authentication/**",
                        "/api/**", "/v2/api-docs", "/swagger-resources/**"
                ).permitAll()
                .mvcMatchers(HttpMethod.GET, "/points").permitAll()
                .mvcMatchers(HttpMethod.GET,"/insurances/users/**").hasRole("CUSTOMER")
                .mvcMatchers("/shifts/**", "/insurances/**", "/cars/**", "/users/**").hasAnyRole("USER","ADMIN")
                .anyRequest().hasRole("ADMIN");
        http.addFilterBefore(new AuthenticationTokenFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
