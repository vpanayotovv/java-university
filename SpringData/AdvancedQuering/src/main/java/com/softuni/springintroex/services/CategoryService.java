package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Category;

import java.io.FileNotFoundException;

public interface CategoryService {
    void seedCategories() throws FileNotFoundException;

    Category getCategoryById(long id);

    int getCategoryCount();
}
