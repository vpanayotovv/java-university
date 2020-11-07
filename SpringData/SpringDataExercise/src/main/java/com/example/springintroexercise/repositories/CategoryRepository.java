package com.example.springintroexercise.repositories;

import com.example.springintroexercise.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
