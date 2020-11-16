package com.example.automappingexercise.services;

import com.example.automappingexercise.dtos.UserLoginDto;
import com.example.automappingexercise.dtos.UserRegDto;

public interface UserService {
    void registerUser(UserRegDto userRegDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggedUserIsAdmin();

}