package com.gustavolyra.twiter_copy.service;

import com.gustavolyra.twiter_copy.config.JwtTokenProvider;
import com.gustavolyra.twiter_copy.dto.UserDto;
import com.gustavolyra.twiter_copy.dto.UserLoginDto;
import com.gustavolyra.twiter_copy.dto.UserRequestDto;
import com.gustavolyra.twiter_copy.entities.User;
import com.gustavolyra.twiter_copy.repository.UserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class AuthService {


    private final JwtTokenProvider jwtTokenProvider;
    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtTokenProvider jwtTokenProvider, ReactiveUserDetailsService reactiveUserDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.reactiveUserDetailsService = reactiveUserDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<String> login(UserLoginDto userLoginDto) {
        return reactiveUserDetailsService.findByUsername(userLoginDto.email())
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found"))).map(jwtTokenProvider::createToken);
    }

    public Mono<UserDto> create(UserRequestDto userDto) {
        return reactiveUserDetailsService
                .findByUsername(userDto.email()).flatMap(userDetails -> Mono.<UserDto>error(new RuntimeException("User already exists"))).switchIfEmpty(userRepository.save(new User(UUID.randomUUID().toString(), userDto.name(), userDto.email(), passwordEncoder.encode(userDto.password()))).map(savedUser -> new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail())));
    }


}
