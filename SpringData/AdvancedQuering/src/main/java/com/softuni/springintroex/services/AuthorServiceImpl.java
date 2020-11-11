package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.utils.CustomFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CustomFileReader customFileReader;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, CustomFileReader customFileReader) {
        this.authorRepository = authorRepository;
        this.customFileReader = customFileReader;
    }

    @Override
    public void seedAuthors() throws FileNotFoundException {
        if (this.authorRepository.count() != 0) {
            return;
        }


        List<String> fileInput = customFileReader.read(GlobalConstants.AUTHORS_FILE_PATH);

        fileInput.forEach(r -> {
            String[] params = r.split("\\s+");

            Author author = new Author(params[0], params[1]);

            this.authorRepository.saveAndFlush(author);

        });

    }

    @Override
    public int geAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(int id) {
        return this.authorRepository.getOne((long) id);
    }
}

