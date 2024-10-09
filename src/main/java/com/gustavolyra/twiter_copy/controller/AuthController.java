package com.gustavolyra.twiter_copy.controller;

import com.gustavolyra.twiter_copy.dto.UserDto;
import com.gustavolyra.twiter_copy.dto.UserRequestDto;
import com.gustavolyra.twiter_copy.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<UserDto>> create(@RequestBody UserRequestDto userMono) {
        log.info("Creating user: {}", userMono);
        return authService.createUser(userMono)
                .map(mono -> ResponseEntity.ok().body(mono));
    }



}
