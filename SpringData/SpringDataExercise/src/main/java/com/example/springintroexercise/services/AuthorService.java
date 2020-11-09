package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Author;

import java.io.FileNotFoundException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws FileNotFoundException;

    int geAllAuthorsCount();

    Author findAuthorById(int id);

    List<Author> getAuthorsByCountOfBooks();
}
