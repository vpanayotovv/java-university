package softuni.jsonexercise.servicies;

import softuni.jsonexercise.domain.entities.Part;

public interface PartService {

    void seedData() throws Exception;

    long getCount();

    Part getPartById(Long id) throws Exception;
}