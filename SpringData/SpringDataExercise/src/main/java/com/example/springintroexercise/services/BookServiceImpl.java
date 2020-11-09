package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Book;
import com.example.springintroexercise.entities.Category;
import com.example.springintroexercise.entities.Constants;
import com.example.springintroexercise.entities.enums.AgeRestriction;
import com.example.springintroexercise.entities.enums.EditionType;
import com.example.springintroexercise.repositories.BookRepository;
import com.example.springintroexercise.utils.CustumFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final CustumFileReader custumFileReader;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(AuthorService authorService, BookRepository bookRepository, CustumFileReader custumFileReader, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.custumFileReader = custumFileReader;
        this.categoryService = categoryService;
    }

    @Transactional
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

            LocalDate releaseDate = LocalDate.parse(params[1], formatter);

            int copies = Integer.parseInt(params[2]);

            BigDecimal price = new BigDecimal(params[3]);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];

            String title = getTitle(params);

            Set<Category> categories = getRandomCategories();

            Book book = new Book(author, editionType, releaseDate, copies, price, ageRestriction, title, categories);

            this.bookRepository.saveAndFlush(book);

        });
    }

    private Set<Category> getRandomCategories() {

        Set<Category> result = new HashSet<>();

        int randomId = ThreadLocalRandom.current().nextInt(1, 4);

        for (int i = 1; i <= randomId; i++) {
            int randomCategory = ThreadLocalRandom.current().nextInt(1, this.categoryService.getCategoryCount() + 1);
            result.add(this.categoryService.getCategoryById(randomCategory));
        }
        return result;

    }

    private String getTitle(String[] params) {
        StringBuilder result = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            result.append(params[i]).append(" ");
        }
        return result.toString().trim();
    }

    private Author getRandomAuthor() {

        int randomId = ThreadLocalRandom.current().nextInt(1, this.authorService.geAllAuthorsCount() + 1);
        return this.authorService.findAuthorById(randomId);

    }
}
