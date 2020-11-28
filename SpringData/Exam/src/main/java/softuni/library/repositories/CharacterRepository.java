package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.entity.Character;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {

    Character findByFirstNameAndLastNameAndAge(String firstName,String lastName,int age);

    List<Character> findAllByAgeIsGreaterThanEqualOrderByBookNameAscLastNameDescAge(int age);
}
