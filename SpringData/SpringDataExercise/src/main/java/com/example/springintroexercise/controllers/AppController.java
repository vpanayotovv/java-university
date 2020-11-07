package com.example.springintroexercise.controllers;

import com.example.springintroexercise.services.AuthorService;
import com.example.springintroexercise.services.BookService;
import com.example.springintroexercise.services.CategoryService;
import com.example.springintroexercise.utils.CustumFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    private final CustumFileReader custumFileReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, CustumFileReader custumFileReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.custumFileReader = custumFileReader;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();

    }
}
