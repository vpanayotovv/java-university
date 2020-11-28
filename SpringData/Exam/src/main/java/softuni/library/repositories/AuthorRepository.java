package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.entity.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByFirstNameAndLastName(String firstName,String lastName);

    Optional<Author> findById(Integer id);
}
