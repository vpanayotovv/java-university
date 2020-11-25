package hiberspring.service.impl;

import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.ProductSeedDto;
import hiberspring.domain.dto.ProductSeedRootDto;
import hiberspring.domain.entity.Branch;
import hiberspring.domain.entity.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.PRODUCTS_FILE_PATH));
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {
        StringBuilder result = new StringBuilder();

        ProductSeedRootDto productSeedRootDto = this.xmlParser.parseXml(ProductSeedRootDto.class, GlobalConstants.PRODUCTS_FILE_PATH);

        for (ProductSeedDto product : productSeedRootDto.getProducts()) {
            if (this.validationUtil.isValid(product)){
                if (this.productRepository.findByName(product.getName()) == null){
                    Product mappedProduct = this.modelMapper.map(product, Product.class);
                    Branch branch = this.branchRepository.findByName(product.getBranch());
                    mappedProduct.setBranch(branch);

                    this.productRepository.saveAndFlush(mappedProduct);

                    result.append(String.format("Successfully imported Product %s",mappedProduct.getName())).append(System.lineSeparator());

                }else {
                    result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                }

            }else {
                result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }

        }

        return result.toString();
    }
}
