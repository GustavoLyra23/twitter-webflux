package com.gustavolyra.twiter_copy.controller;

import com.gustavolyra.twiter_copy.dto.UserDto;
import com.gustavolyra.twiter_copy.dto.UserLoginDto;
import com.gustavolyra.twiter_copy.dto.UserRequestDto;
import com.gustavolyra.twiter_copy.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create")
    public Mono<UserDto> create(@RequestBody UserRequestDto userDto) {
        return authService.create(userDto);
    }


    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody UserLoginDto userLoginDto) {
        return authService.login(userLoginDto)
                .map(mono -> ResponseEntity.ok().body(Map.of("token", mono)));
    }



}
