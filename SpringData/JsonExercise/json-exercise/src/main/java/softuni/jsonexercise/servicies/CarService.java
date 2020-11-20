package softuni.jsonexercise.servicies;

import softuni.jsonexercise.domain.entities.Car;

public interface CarService {

    void seedData() throws Exception;

    Car getCarById(Long id);
}
