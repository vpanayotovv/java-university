package softuni.examprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.examprep.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
}
