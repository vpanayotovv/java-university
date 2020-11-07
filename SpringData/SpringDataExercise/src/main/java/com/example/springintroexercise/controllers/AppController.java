package com.example.springintroexercise.controllers;

import com.example.springintroexercise.entities.Constants;
import com.example.springintroexercise.services.CategoryService;
import com.example.springintroexercise.utils.CustumFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;

    private final CustumFileReader custumFileReader;

    @Autowired
    public AppController(CategoryService categoryService, CustumFileReader custumFileReader) {
        this.categoryService = categoryService;
        this.custumFileReader = custumFileReader;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.seedCategories();

    }
}
