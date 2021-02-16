package softuni.examprep.service.impl;

import org.springframework.stereotype.Service;
import softuni.examprep.model.entity.Category;
import softuni.examprep.model.entity.enums.CategoryName;
import softuni.examprep.repository.CategoryRepository;
import softuni.examprep.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedData() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(category -> createCategory(category, String.format("This is the %S category", category.name())));
        }
    }

    @Override
    public Category getByName(CategoryName name) {
       return categoryRepository.findByName(name).orElse(null);
    }

    private void createCategory(CategoryName name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        this.categoryRepository.saveAndFlush(category);
    }
}
