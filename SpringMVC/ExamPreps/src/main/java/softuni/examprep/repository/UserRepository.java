package softuni.examprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.examprep.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
