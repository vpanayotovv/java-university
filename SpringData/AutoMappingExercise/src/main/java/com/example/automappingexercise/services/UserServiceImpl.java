package com.example.automappingexercise.services;

import com.example.automappingexercise.domain.entities.Role;
import com.example.automappingexercise.domain.entities.User;
import com.example.automappingexercise.dtos.UserDto;
import com.example.automappingexercise.dtos.UserLoginDto;
import com.example.automappingexercise.dtos.UserRegDto;
import com.example.automappingexercise.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegDto userRegDto) {

        User user = this.modelMapper.map(userRegDto,User.class);

        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {

        User user = this.userRepository.findByEmail(userLoginDto.getEmail());

        if (user == null){
            System.out.println("Incorrect username / password");
        }else {
            this.userDto = this.modelMapper.map(user ,UserDto.class);
            System.out.println("Successfully logged in " + user.getFullName());
        }

    }

    @Override
    public void logout() {
        if (this.userDto == null){
            System.out.println("Cannot log out. No user was logged in.");
        }else {
            String name = this.userDto.getFullName();
            this.userDto = null;
            System.out.println("User " + name + " successfully logged out");
        }
    }
}
