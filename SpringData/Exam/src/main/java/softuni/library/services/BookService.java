package softuni.library.services;

import softuni.library.models.entity.Book;

import java.io.IOException;

public interface BookService {
    boolean areImported();
    String readBooksFileContent() throws IOException;
    String importBooks() throws IOException;

    Book getBookById(Integer id);
}
