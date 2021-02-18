package softuni.examprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.examprep.model.dto.ProductAddBindingModel;
import softuni.examprep.model.entity.Product;
import softuni.examprep.model.entity.enums.CategoryName;
import softuni.examprep.model.view.ProductViewModel;
import softuni.examprep.repository.ProductRepository;
import softuni.examprep.service.CategoryService;
import softuni.examprep.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductAddBindingModel productAddBindingModel) {
        Product product = modelMapper.map(productAddBindingModel, Product.class);
        product.setCategory(categoryService.getByName(productAddBindingModel.getCategory()));
        productRepository.saveAndFlush(product);
    }

    @Override
    public BigDecimal getTotalPrice() {
        if (productRepository.getSumOfProducts() == null){
            return new BigDecimal(0);
        }
       return productRepository.getSumOfProducts();
    }

    @Override
    public List<ProductViewModel> getAllByCategory(CategoryName categoryName) {
       return productRepository.findAllByCategory_Name(categoryName).stream().map(product -> modelMapper.map(product,ProductViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void buyProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
