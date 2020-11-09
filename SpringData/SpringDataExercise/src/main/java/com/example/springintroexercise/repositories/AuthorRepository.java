package com.example.springintroexercise.repositories;

import com.example.springintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a order by a.books.size desc")
    List<Author> findAuthorsByBooksCount();
}
