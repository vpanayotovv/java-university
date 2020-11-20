package softuni.jsonexercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonexercise.domain.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer getById(Long id);

}
