package com.gira.giraexamprep.service;

import com.gira.giraexamprep.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel getByEmail(String username);
}
