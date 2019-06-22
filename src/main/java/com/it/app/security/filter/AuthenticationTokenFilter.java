package com.it.app.security.filter;

import com.it.app.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication Token handling class
 */
@AllArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private TokenService tokenService;
    private UserDetailsService userDetailsService;

    /**
     * Validate Token from request. Create UsernamePasswordAuthenticationToken for username from request Token.
     * Register this authentication in SecurityContext
     *
     * @param httpRequest         - the ServletRequest object contains the client's request
     * @param httpServletResponse - the ServletResponse object contains the filter's response
     * @param chain               - the FilterChain for invoking the next filter or the resource
     * @throws IOException      - if an I/O related error has occurred during the processing
     * @throws ServletException - if an exception occurs that interferes with the filter's normal operation
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        String token = getToken(httpRequest);
        if (token != null && tokenService.validate(token)) {
            String username = tokenService.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(httpRequest, httpServletResponse);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        return authHeader != null && authHeader.startsWith(BEARER)
                ? authHeader.replace(BEARER, "") : authHeader;
    }
}
