package com.example.automappingexercise.services;

import com.example.automappingexercise.dtos.GameAddDto;
import com.example.automappingexercise.dtos.GameEditDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(GameEditDto gameEditDto);

    void deleteGame(Long id);
}
