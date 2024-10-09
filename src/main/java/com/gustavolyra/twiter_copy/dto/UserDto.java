package com.gustavolyra.twiter_copy.dto;

import com.gustavolyra.twiter_copy.entities.User;
import lombok.Getter;

@Getter
public class UserDto {

    private final String id;
    private final String name;
    private final String email;

    public UserDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

}
