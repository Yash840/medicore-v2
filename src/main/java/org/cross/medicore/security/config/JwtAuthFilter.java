package org.cross.medicore.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cross.medicore.security.jwt.JwtService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        log.debug("Processing authentication for request: {} {}", request.getMethod(), requestUri);

        String authHeader = request.getHeader("Authorization");

        if(Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")){
            log.debug("No Bearer token found in Authorization header for {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        log.debug("Extracted JWT token from Authorization header");

        try {
            String username = jwtService.extractUsername(jwt);
            log.debug("Extracted username from JWT: {}", username);

            if(!Objects.isNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
                log.debug("Attempting to authenticate user: {}", username);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                log.debug("Loaded user details for: {}", username);

                if(jwtService.isValidToken(jwt, userDetails)){
                    log.info("JWT token validated successfully for user: {}", username);

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.info("User authenticated successfully: {} with authorities: {}", username, userDetails.getAuthorities());
                } else {
                    log.warn("JWT token validation failed for user: {}", username);
                }
            } else if(!Objects.isNull(username)) {
                log.debug("User {} already authenticated in security context", username);
            } else {
                log.warn("Unable to extract username from JWT token");
            }
        } catch (Exception e) {
            log.error("Error processing JWT authentication: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }
}
