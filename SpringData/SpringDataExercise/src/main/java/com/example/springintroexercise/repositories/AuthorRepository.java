package com.example.springintroexercise.repositories;

import com.example.springintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
