package com.softuni.springintroex.controllers;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.CustomConsoleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.util.Map;


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

        seedData();

        System.out.println("If you want to end the process write \"exit\"!");
        System.out.println("Write the number of Query you want[1,14]:");
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
                    this.bookService.getBooksInPriceRange().forEach(b ->
                            System.out.printf("%s $%.2f%n",
                                    b.getTitle(),
                                    b.getPrice()));
                    break;

                case "4":
                    System.out.println("Enter year:");
                    String year = reader.read();
                    this.bookService.getBooksNotReleasedInGivenYear(year).forEach(b -> System.out.println(b.getTitle()));
                    break;
                case "5":
                    System.out.println("Enter date:");
                    String date = reader.read();
                    this.bookService.getBooksReleasedBeforeDate(date)
                            .forEach(b -> System.out.printf("%s %s $%.2f%n",
                                    b.getTitle(),
                                    b.getEditionType(),
                                    b.getPrice()));
                    break;

                case "6":
                    System.out.println("Enter string:");
                    String end = reader.read();
                    this.authorService.getAllByFirstNameEndWith(end).forEach(a ->
                            System.out.printf(" %s %s%n",
                                    a.getFirstName(),
                                    a.getLastName()));
                    break;

                case "7":
                    System.out.println("Enter string:");
                    String text = reader.read();
                    this.bookService.getBooksTitleContains(text).forEach(b -> System.out.println(b.getTitle()));
                    break;

                case "8":
                    System.out.println("Enter string:");
                    String start = reader.read();
                    this.bookService.getBooksAuthorsLastNameStartWith(start).forEach(b ->
                            System.out.printf("%s ( %s %s )%n",
                                    b.getTitle(),
                                    b.getAuthor().getFirstName(),
                                    b.getAuthor().getLastName()));
                    break;

                case "9":
                    System.out.println("Enter title's length:");
                    int length = Integer.parseInt(reader.read());
                    int countOfBooks = this.bookService.getCountOfBooks(length);
                    System.out.println(countOfBooks);
                    break;
                case "10":
                    Map<String,Integer> authorsCopies = this.authorService.getAuthorsCopies();

                    for (Map.Entry<String, Integer> entry : authorsCopies.entrySet()) {
                        System.out.printf("%s - %d%n",entry.getKey(),entry.getValue());
                    }
                    break;
                case "11":
                    System.out.println("Enter title:");
                    String titleOfBook = reader.read();
                    this.bookService.getBooksByTitleName(titleOfBook).forEach(b -> System.out.print(b + " "));
                    break;

                default:
                    System.out.println("Write the number of Query you want[1,14]:");
            }

            input = reader.read();
        }

    }

    private void seedData() throws FileNotFoundException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
