package com.gustavolyra.twiter_copy.controller;

import com.gustavolyra.twiter_copy.dto.UserDto;
import com.gustavolyra.twiter_copy.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public Flux<UserDto> findAll() {
        return userService.findAll();
    }


}
