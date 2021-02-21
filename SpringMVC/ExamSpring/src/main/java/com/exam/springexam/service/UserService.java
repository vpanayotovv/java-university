package com.exam.springexam.service;

import com.exam.springexam.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel getByUsernameAndPassword(String username, String password);
}
