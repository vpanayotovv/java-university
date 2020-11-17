package softuni.jsonexercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonexercise.domain.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
}
