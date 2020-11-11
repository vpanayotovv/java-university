package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.utils.CustomFileReader;
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

        List<String> fileInput = customFileReader.read(GlobalConstants.CATEGORIES_FILE_PATH);

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
