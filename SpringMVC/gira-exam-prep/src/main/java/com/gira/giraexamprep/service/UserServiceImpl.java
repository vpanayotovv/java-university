package com.gira.giraexamprep.service;

import com.gira.giraexamprep.model.entity.UserEntity;
import com.gira.giraexamprep.model.service.UserServiceModel;
import com.gira.giraexamprep.repository.UserRepository;
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
    public void register(UserServiceModel userServiceModel){
        UserEntity userEntity = modelMapper.map(userServiceModel,UserEntity.class);
        userRepository.save(userEntity);
    }

    @Override
    public UserServiceModel getByEmail(String email) {
        return userRepository.findByEmail(email).map(user -> modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }
}
