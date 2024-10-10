package com.gustavolyra.twiter_copy.service;

import com.gustavolyra.twiter_copy.dto.user.UserDto;
import com.gustavolyra.twiter_copy.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Flux<UserDto> findAll() {
        return userRepository.findAll().map(UserDto::new);
    }


}
