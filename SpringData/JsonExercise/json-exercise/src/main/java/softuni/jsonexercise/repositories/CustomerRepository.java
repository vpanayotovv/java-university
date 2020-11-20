package softuni.jsonexercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.jsonexercise.domain.entities.Customer;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer getById(Long id);

    @Query("select c from Customer c order by c.birthDate asc")
    List<Customer> getCustomers();
}
