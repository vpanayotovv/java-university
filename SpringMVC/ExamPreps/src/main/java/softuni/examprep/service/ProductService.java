package softuni.examprep.service;

import softuni.examprep.model.dto.ProductAddBindingModel;

import java.math.BigDecimal;

public interface ProductService {
    void addProduct(ProductAddBindingModel productAddBindingModel);

    BigDecimal getTotalPrice();
}
