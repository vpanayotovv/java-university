package com.example.automappingexercise.services;

import com.example.automappingexercise.domain.entities.Game;
import com.example.automappingexercise.dtos.GameAddDto;
import com.example.automappingexercise.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {

        if (this.userService.isLoggedUserIsAdmin()) {
            Game game = this.modelMapper.map(gameAddDto, Game.class);
            this.gameRepository.saveAndFlush(game);

        } else {
            System.out.println("Logged user is not Admin!");
        }
    }
}
