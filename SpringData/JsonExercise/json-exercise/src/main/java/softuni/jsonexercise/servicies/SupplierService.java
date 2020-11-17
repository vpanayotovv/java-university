package softuni.jsonexercise.servicies;

import softuni.jsonexercise.domain.entities.Supplier;

import java.io.IOException;

public interface SupplierService {

    void seedData() throws IOException;

    int getCount();

    Supplier getById(Long id) throws Exception;

}
