package com.example.automappingexercise.services;

import com.example.automappingexercise.domain.entities.Game;
import com.example.automappingexercise.dtos.GameAddDto;
import com.example.automappingexercise.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        Game game = this.modelMapper.map(gameAddDto,Game.class);
        this.gameRepository.saveAndFlush(game);
    }
}
