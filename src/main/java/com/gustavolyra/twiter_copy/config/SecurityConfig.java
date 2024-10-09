package com.gustavolyra.twiter_copy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtAuthenticationManager jwtAuthManager) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/v1/auth/**").permitAll()
                        .anyExchange().authenticated()
                )
                .authenticationManager(jwtAuthManager)
                .addFilterAt(jwtWebFilter(jwtAuthManager), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }


    @Bean
    public AuthenticationWebFilter jwtWebFilter(JwtAuthenticationManager jwtAuthManager) {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(jwtAuthManager);
        filter.setServerAuthenticationConverter(new JwtServerAuthenticationConverter()); // Custom JWT converter
        return filter;
    }

    @Bean
    public JwtAuthenticationManager jwtAuthManager(JwtTokenProvider jwtTokenProvider, ReactiveUserDetailsService userDetailsService) {
        return new JwtAuthenticationManager(jwtTokenProvider, userDetailsService);
    }

}
