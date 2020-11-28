package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.service.CarService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;

    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/cars.json";

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, RacerRepository racerRepository, FileUtil fileUtil) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(CARS_JSON_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) throws IOException {
        StringBuilder result = new StringBuilder();

        CarImportDto[] carImportDtos = this.gson.fromJson(readCarsJsonFile(), CarImportDto[].class);
        for (CarImportDto car : carImportDtos) {
            if (this.validationUtil.isValid(car)){
                if (this.carRepository.findByBrandAndModelAndYearOfProduction(car.getBrand(),car.getModel(),car.getYearOfProduction()) == null){
                    Car mappedCar = this.modelMapper.map(car, Car.class);
                    Racer racer = this.racerRepository.findByName(car.getRacerName());
                    mappedCar.setRacer(racer);
                    this.carRepository.saveAndFlush(mappedCar);

                    result.append(String.format(
                            "Successfully imported Car - %s %s @ %d",
                            mappedCar.getBrand(),
                            mappedCar.getModel(),
                            mappedCar.getYearOfProduction()
                    )).append(System.lineSeparator());

                }else {
                    result.append(
                            Constants.DUPLICATE_DATA_MESSAGE
                    ).append(System.lineSeparator());
                }
            }else {
                result.append(
                        Constants.INCORRECT_DATA_MESSAGE
                ).append(System.lineSeparator());
            }
        }


        return result.toString();
    }
}
