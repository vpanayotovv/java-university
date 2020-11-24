package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

}
