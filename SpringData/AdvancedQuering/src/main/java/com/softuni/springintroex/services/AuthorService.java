package com.softuni.springintroex.services;


import com.softuni.springintroex.entities.Author;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface AuthorService {

    void seedAuthors() throws FileNotFoundException;

    int geAllAuthorsCount();

    Author findAuthorById(int id);

    List<Author> getAllByFirstNameEndWith(String end);

    Map<String,Integer> getAuthorsCopies();
}
