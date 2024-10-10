package com.gustavolyra.twiter_copy.dto.user;

import com.gustavolyra.twiter_copy.entities.User;
import com.gustavolyra.twiter_copy.enums.Role;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDto {

    private final String id;
    private final String name;
    private final String email;
    private final List<Role> roles = new ArrayList<>();

    public UserDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        entity.getAuthorities()
                .forEach(role -> this.roles.add(Role.valueOf(role.getAuthority())));
    }

}
