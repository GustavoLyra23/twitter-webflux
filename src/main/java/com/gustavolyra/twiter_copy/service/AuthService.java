package com.gustavolyra.twiter_copy.service;

import com.gustavolyra.twiter_copy.dto.UserDto;
import com.gustavolyra.twiter_copy.dto.UserRequestDto;
import com.gustavolyra.twiter_copy.entities.User;
import com.gustavolyra.twiter_copy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDto> createUser(UserRequestDto dto) {
        log.debug("Creating user with email {}", dto.email());
        return userRepository.findByEmail(dto.email())
                .flatMap(user -> Mono.error(new IllegalArgumentException("User already exists")))
                .switchIfEmpty(userRepository.save(new User(UUID.randomUUID().toString(), dto.name(), dto.email(), dto.password()))
                        .map(UserDto::new)).cast(UserDto.class);
    }
}
