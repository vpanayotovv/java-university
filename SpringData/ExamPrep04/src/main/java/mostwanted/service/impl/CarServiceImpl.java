package mostwanted.service.impl;

import mostwanted.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/cars.json";

    @Override
    public Boolean carsAreImported() {
        //TODO: Implement me
        return false;
    }

    @Override
    public String readCarsJsonFile() {
        //TODO: Implement me
        return null;
    }

    @Override
    public String importCars(String carsFileContent) {
        //TODO: Implement me
        return null;
    }
}
