package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.entity.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findByNameAndEdition(String name,int edition);

    Optional<Book> findById(Integer id);
}
