package softuni.jsonexercise.servicies;


import softuni.jsonexercise.domain.entities.Customer;

import java.io.FileNotFoundException;

public interface CustomerService {

    void seedData() throws FileNotFoundException;

    Customer getById(Long id);
}
