package com.examprep.andeys.service;

import com.examprep.andeys.model.entity.User;
import com.examprep.andeys.model.service.UserServiceModel;
import com.examprep.andeys.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel,User.class);
        userRepository.save(user);
    }
}
