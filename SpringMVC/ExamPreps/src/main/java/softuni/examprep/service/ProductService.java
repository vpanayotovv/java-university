package softuni.examprep.service;

import softuni.examprep.model.dto.ProductAddBindingModel;
import softuni.examprep.model.entity.Category;
import softuni.examprep.model.entity.Product;
import softuni.examprep.model.entity.enums.CategoryName;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductAddBindingModel productAddBindingModel);

    BigDecimal getTotalPrice();

    List<Product> getAllByCategory(CategoryName categoryName);
}
