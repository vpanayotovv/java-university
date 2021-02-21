package com.exam.springexam.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private String id;

    private String username;

    private String fullName;

    private String password;

    private String email;

}