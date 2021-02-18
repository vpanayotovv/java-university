package softuni.examprep.service;

import softuni.examprep.model.dto.ProductAddBindingModel;
import softuni.examprep.model.entity.enums.CategoryName;
import softuni.examprep.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductAddBindingModel productAddBindingModel);

    BigDecimal getTotalPrice();

    List<ProductViewModel> getAllByCategory(CategoryName categoryName);

    void buyProduct(String id);

    void buyAllProducts();
}
