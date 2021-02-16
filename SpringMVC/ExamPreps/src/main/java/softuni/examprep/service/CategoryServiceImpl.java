package softuni.examprep.service;

import org.springframework.stereotype.Service;
import softuni.examprep.model.entity.Category;
import softuni.examprep.model.entity.enums.CategoryName;
import softuni.examprep.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedData() {
        createCategory(CategoryName.FOOD, "This is the Food category");
        createCategory(CategoryName.DRINK, "This is the Drink category");
        createCategory(CategoryName.HOUSEHOLD, "This is the Household category");
        createCategory(CategoryName.OTHER, "This is the Other category");
    }

    private void createCategory(CategoryName food, String description) {
        Category category = new Category();
        category.setName(food);
        category.setDescription(description);
        this.categoryRepository.saveAndFlush(category);
    }
}
