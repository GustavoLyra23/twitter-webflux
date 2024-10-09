package com.gustavolyra.twiter_copy.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;

public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenProvider jwtTokenProvider;
    private final ReactiveUserDetailsService userDetailsService;

    public JwtAuthenticationManager(JwtTokenProvider jwtTokenProvider, ReactiveUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String username = jwtTokenProvider.getUsernameFromToken(token);

        if (username == null || !jwtTokenProvider.validateToken(token)) {
            return Mono.empty();
        }

        return userDetailsService.findByUsername(username)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                ));
    }


}