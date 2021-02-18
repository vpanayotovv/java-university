package com.examprep.andeys.service;

import com.examprep.andeys.model.entity.Category;
import com.examprep.andeys.model.entity.enums.CategoryName;

public interface CategoryService {

    void initData();

    Category findByName(CategoryName categoryName);
}
