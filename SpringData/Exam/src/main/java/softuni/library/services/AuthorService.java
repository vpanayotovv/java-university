package softuni.library.services;

import softuni.library.models.entity.Author;

import java.io.IOException;

public interface AuthorService {
    boolean areImported();
    String readAuthorsFileContent() throws IOException;
    String importAuthors() throws IOException;

    Author getAuthorbyId(Integer id ) throws Exception;
}
