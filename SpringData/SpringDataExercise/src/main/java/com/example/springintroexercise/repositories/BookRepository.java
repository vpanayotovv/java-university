package com.example.springintroexercise.repositories;

import com.example.springintroexercise.entities.Book;
import com.example.springintroexercise.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
