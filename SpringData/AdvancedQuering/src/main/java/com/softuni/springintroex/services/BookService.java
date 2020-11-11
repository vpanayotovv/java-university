package com.softuni.springintroex.services;


import com.softuni.springintroex.entities.Book;

import java.io.FileNotFoundException;
import java.util.List;

public interface BookService {

    void seedBooks() throws FileNotFoundException;

    List<Book> getBooksByAgeRestriction(String ageRes);

    List<Book> getGoldBooksWithLessThen5000Copies();

    List<Book> getBooksInPriceRange();

    List<Book> getBooksNotReleasedInGivenYear(String year);

    List<Book> getBooksReleasedBeforeDate(String date);

    List<Book> getBooksTitleContains(String text);

}
