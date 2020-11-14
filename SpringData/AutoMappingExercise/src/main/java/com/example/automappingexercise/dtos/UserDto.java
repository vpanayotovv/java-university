package com.example.automappingexercise.dtos;

import com.example.automappingexercise.domain.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
