package com.softuni.springintroex.controllers;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.CustomConsoleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class Engine implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final CustomConsoleReader reader;

    @Autowired
    public Engine(CategoryService categoryService, AuthorService authorService, BookService bookService, CustomConsoleReader reader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.reader = reader;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
//        System.out.println("If you want to end the process write \"exit\"!");
//        System.out.println("Write the number of Query you want[1,4]:");
//        String input = reader.read();
//
//        while (!input.equals("exit")) {
//            switch (input) {
//                case "1":
//                    System.out.println("Are you shore u want to print all 102 books (yes/no) :");
//
//
//                default:
//                    System.out.println("Write the number of Query you want[1,4]:");
//            }
//
//            input = reader.read();
//        }

    }
}
