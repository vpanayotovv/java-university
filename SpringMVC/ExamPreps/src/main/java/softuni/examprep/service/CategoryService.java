package softuni.examprep.service;

import softuni.examprep.model.entity.Category;
import softuni.examprep.model.entity.enums.CategoryName;

public interface CategoryService {
    void seedData();

    Category getByName(CategoryName name);
}
