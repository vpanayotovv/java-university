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
        System.out.println("If you want to end the process write \"exit\"!");
        System.out.println("Write the number of Query you want[1,4]:");
        String input = reader.read();

        while (!input.equals("exit")) {
            switch (input) {
                case "1":
                    System.out.println("Enter age restriction:");
                    String ageRes = reader.read();
                    this.bookService.getBooksByAgeRestriction(ageRes).forEach(b -> System.out.println(b.getTitle()));
                    break;

                case "2":
                    this.bookService.getGoldBooksWithLessThen5000Copies().forEach(b -> System.out.println(b.getTitle()));
                    break;

                case "3":
                    this.bookService.getBooksInPriceRange().forEach(b -> System.out.printf("%s $%.2f%n", b.getTitle(), b.getPrice()));
                    break;

                case "4":
                    int year = Integer.parseInt(reader.read());
                    this.bookService.getBooksNotReleasedInGivenYear(year).forEach(b -> System.out.println(b.getTitle()));
                    break;

                default:
                    System.out.println("Write the number of Query you want[1,4]:");
            }

            input = reader.read();
        }

    }
}
