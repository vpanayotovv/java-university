package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Category;

import java.io.FileNotFoundException;

public interface CategoryService {
    void seedCategories() throws FileNotFoundException;

    Category getCategoryById(long id);

    int getCategoryCount();
}
