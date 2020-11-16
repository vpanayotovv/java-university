package com.example.automappingexercise.services;

import com.example.automappingexercise.domain.entities.Game;
import com.example.automappingexercise.dtos.GameAddDto;
import com.example.automappingexercise.dtos.GameEditDto;
import com.example.automappingexercise.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void editGame(GameEditDto gameEditDto) {
        if (this.userService.isLoggedUserIsAdmin()) {
            Optional<Game> game = this.gameRepository.findById(gameEditDto.getId());
            if (!game.isPresent()){
                System.out.println("No such ID!");
            }else {

                Game gameDb = this.modelMapper.map(gameEditDto,Game.class);
                gameDb.setTitle(game.get().getTitle());
                gameDb.setDescription(game.get().getDescription());
                gameDb.setImageThumbnail(game.get().getImageThumbnail());
                gameDb.setReleaseDate(game.get().getReleaseDate());
                gameDb.setTrailer(game.get().getTrailer());
                this.gameRepository.saveAndFlush(gameDb);
                System.out.println("Edited " + gameDb.getTitle());
            }
        } else {
            System.out.println("Logged user is not Admin!");
        }
    }

    @Override
    public void deleteGame(Long id) {
        if (this.userService.isLoggedUserIsAdmin()) {
            Optional<Game> game = this.gameRepository.findById(id);
            if(game.isPresent()){
                this.gameRepository.delete(game.get());
                System.out.println("Deleted " + game.get().getTitle());
            }else {
                System.out.println("No such ID!");
            }

        } else {
            System.out.println("Logged user is not Admin!");
        }
    }
}
