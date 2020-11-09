package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Constants;
import com.example.springintroexercise.entities.enums.EditionType;
import com.example.springintroexercise.repositories.BookRepository;
import com.example.springintroexercise.utils.CustumFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final CustumFileReader custumFileReader;

    @Autowired
    public BookServiceImpl(AuthorService authorService, BookRepository bookRepository, CustumFileReader custumFileReader) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.custumFileReader = custumFileReader;
    }

    @Override
    public void seedBooks() throws FileNotFoundException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        List<String> fileInput = custumFileReader.read(Constants.BOOKS_PATH);

        fileInput.forEach(r -> {
            String[] params = r.split("\\s+");

            Author author = getRandomAuthor();

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

            LocalDate releaseDate = LocalDate.parse(params[1],formatter);

            int copies = Integer.parseInt(params[2]);

            System.out.println();

        });
    }

    private Author getRandomAuthor() {

        int randomId = ThreadLocalRandom.current().nextInt(this.authorService.geAllAuthorsCount() + 1);
        return this.authorService.findAuthorById(randomId);

    }
}
