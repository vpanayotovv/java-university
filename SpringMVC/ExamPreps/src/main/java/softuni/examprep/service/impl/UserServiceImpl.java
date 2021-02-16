package softuni.examprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.examprep.model.entity.User;
import softuni.examprep.model.service.UserServiceModel;
import softuni.examprep.repository.UserRepository;
import softuni.examprep.service.UserService;

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
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel getByUsername(String username) {
       return userRepository.findByUsername(username).map(user -> modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }
}
