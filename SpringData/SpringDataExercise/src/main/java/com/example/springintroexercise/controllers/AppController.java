package com.example.springintroexercise.controllers;

import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Book;
import com.example.springintroexercise.services.AuthorService;
import com.example.springintroexercise.services.BookService;
import com.example.springintroexercise.services.CategoryService;
import com.example.springintroexercise.utils.CustomConsoleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final CustomConsoleReader reader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, CustomConsoleReader reader) {
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
                    System.out.println("Are you shore u want to print all 102 books (yes/no) :");
                    String answer = reader.read();
                    if (answer.equals("yes")) {
                        List<Book> books = this.bookService.getAllBooksAfter2000();
                        for (Book book : books) {
                            System.out.println(book.getTitle());
                        }
                    } else {
                        System.out.println("good choice :)");
                        break;
                    }
                    break;
                case "2":
                    List<Book> books = bookService.getAllBooksBefore1990();
                    for (Book book : books) {
                        System.out.printf("%s %s%n", book.getAuthor().getFirstName(), book.getAuthor().getLastName());
                    }
                    break;
                case "3":
                    List<Author> authors = this.authorService.getAuthorsByCountOfBooks();

                    authors.forEach(a ->{
                        System.out.printf("%s %s %d%n",a.getFirstName(),a.getLastName(),a.getBooks().size());
                    });

                    break;
                case "4":

                    List<Book> booksByAuthor = this.bookService.getBooksByGeorgePowell();

                    for (Book book : booksByAuthor) {
                        System.out.printf("%s %s %d%n",book.getTitle(),book.getReleaseDate(),book.getCopies());
                    }

                    break;
                    default:
                        System.out.println("Write the number of Query you want[1,4]:");
            }

            input = reader.read();
        }

    }
}
