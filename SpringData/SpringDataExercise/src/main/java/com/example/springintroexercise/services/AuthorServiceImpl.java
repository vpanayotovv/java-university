package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Constants;
import com.example.springintroexercise.repositories.AuthorRepository;
import com.example.springintroexercise.utils.CustumFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CustumFileReader custumFileReader;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, CustumFileReader custumFileReader) {
        this.authorRepository = authorRepository;
        this.custumFileReader = custumFileReader;
    }

    @Override
    public void seedAuthors() throws FileNotFoundException {
        if (this.authorRepository.count() != 0) {
            return;
        }


        List<String> fileInput = custumFileReader.read(Constants.AUTHORS_PATH);

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
