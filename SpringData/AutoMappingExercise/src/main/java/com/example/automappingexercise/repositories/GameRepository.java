package com.example.automappingexercise.repositories;

import com.example.automappingexercise.domain.entities.Game;
import com.example.automappingexercise.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

}
