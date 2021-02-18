package softuni.examprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.examprep.model.entity.Product;
import softuni.examprep.model.entity.enums.CategoryName;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    @Query("select sum(p.price) from Product p")
    BigDecimal getSumOfProducts();

    List<Product> findAllByCategory_Name(CategoryName categoryName);
}
