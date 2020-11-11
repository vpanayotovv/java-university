package com.softuni.springintroex.services;


import com.softuni.springintroex.entities.Author;

import java.io.FileNotFoundException;

public interface AuthorService {

    void seedAuthors() throws FileNotFoundException;

    int geAllAuthorsCount();

    Author findAuthorById(int id);
}
