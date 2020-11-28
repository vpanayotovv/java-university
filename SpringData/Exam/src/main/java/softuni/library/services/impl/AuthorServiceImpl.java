package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.AuthorsSeedDto;
import softuni.library.models.entity.Author;
import softuni.library.repositories.AuthorRepository;
import softuni.library.services.AuthorService;
import softuni.library.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final String AUTHOR_PATH =
            "src/main/resources/files/json/authors.json";

    public AuthorServiceImpl(AuthorRepository authorRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public String readAuthorsFileContent() throws IOException {
        return Files.readString(Path.of(AUTHOR_PATH));
    }

    @Override
    public String importAuthors() throws IOException {
        StringBuilder result = new StringBuilder();

        AuthorsSeedDto[] authorsSeedDtos = this.gson.fromJson(readAuthorsFileContent(), AuthorsSeedDto[].class);

        for (AuthorsSeedDto author : authorsSeedDtos) {
            if (this.validationUtil.isValid(author)) {
                if (this.authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName()) == null) {
                    Author mappedAuthor = this.modelMapper.map(author, Author.class);

                    this.authorRepository.saveAndFlush(mappedAuthor);

                    result.append(String.format(
                            "Successfully imported Author: %s %s - %s",
                            mappedAuthor.getFirstName(),
                            mappedAuthor.getLastName(),
                            mappedAuthor.getBirthTown())
                    ).append(System.lineSeparator());

                } else {
                    result.append("Invalid Author").append(System.lineSeparator());
                }
            } else {
                result.append("Invalid Author").append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    public Author getAuthorbyId(Integer id) throws Exception {

        return this.authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such Author"));

    }
}
