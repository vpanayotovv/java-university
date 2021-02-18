package com.examprep.andeys.repository;

import com.examprep.andeys.model.entity.Category;
import com.examprep.andeys.model.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryName categoryName);
}
