package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Book;

import java.io.FileNotFoundException;
import java.util.List;

public interface BookService {

    void seedBooks() throws FileNotFoundException;

    List<Book> getAllBooksAfter2000();

    List<Book> getAllBooksBefore1990();

    List<Book> getBooksByGeorgePowell();

}
