package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.BookSeedDto;
import softuni.library.models.entity.Author;
import softuni.library.models.entity.Book;
import softuni.library.repositories.BookRepository;
import softuni.library.services.AuthorService;
import softuni.library.services.BookService;
import softuni.library.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final AuthorService authorService;
    private final String BOOKS_PATH =
            "src/main/resources/files/json/books.json";

    public BookServiceImpl(BookRepository bookRepository,
                           Gson gson,
                           ValidationUtil validationUtil,
                           ModelMapper modelMapper,
                           AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFileContent() throws IOException {
        return Files.readString(Path.of(BOOKS_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder result = new StringBuilder();
            BookSeedDto[] bookSeedDtos = this.gson.fromJson(readBooksFileContent(), BookSeedDto[].class);
            for (BookSeedDto book : bookSeedDtos) {
                if (this.validationUtil.isValid(book)) {
                    if (this.bookRepository.findByNameAndEdition(book.getName(), book.getEdition()) == null) {

                        Book mappedBook = this.modelMapper.map(book, Book.class);
                        Author author;
                        try {
                            author = this.authorService.getAuthorbyId(book.getAuthor());
                            mappedBook.setAuthor(author);
                            this.bookRepository.saveAndFlush(mappedBook);
                            result.append(String.format("Successfully imported Book: %s written in %s", mappedBook.getName(), mappedBook.getWritten().toString())).append(System.lineSeparator());
                        } catch (Exception e) {
                            result.append(e.getMessage()).append(System.lineSeparator());
                        }



                    } else {
                        result.append("Invalid Book").append(System.lineSeparator());
                    }
                } else {
                    result.append("Invalid Book").append(System.lineSeparator());
                }
            }
            return result.toString();
    }

    @Override
    public Book getBookById(Integer id) {

       return this.bookRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("No such Book"));

    }
}
