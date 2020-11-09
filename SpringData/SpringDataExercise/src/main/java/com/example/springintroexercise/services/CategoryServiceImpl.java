package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Category;
import com.example.springintroexercise.entities.Constants;
import com.example.springintroexercise.repositories.CategoryRepository;
import com.example.springintroexercise.utils.CustomFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CustomFileReader customFileReader;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CustomFileReader customFileReader) {
        this.categoryRepository = categoryRepository;
        this.customFileReader = customFileReader;
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        List<String> fileInput = customFileReader.read(Constants.CATEGORIES_PATH);

        fileInput.forEach(r -> {
            Category category = new Category(r);

            this.categoryRepository.saveAndFlush(category);
        });


    }

    @Override
    public Category getCategoryById(long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public int getCategoryCount() {
        return (int) this.categoryRepository.count();
    }
}
