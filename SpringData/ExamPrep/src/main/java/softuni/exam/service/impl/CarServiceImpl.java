package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarImportDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {

    private final Gson gson;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final String CARS_PATH = "src/main/resources/files/json/cars.json";

    @Autowired
    public CarServiceImpl(Gson gson, CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gson = gson;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(CARS_PATH)));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();

        CarImportDto[] carImportDtos = this.gson.fromJson(this.readCarsFileContent(), CarImportDto[].class);

        for (CarImportDto carImportDto : carImportDtos) {
            if (validationUtil.isValid(carImportDto)){
                Car mappedCar = this.modelMapper.map(carImportDto, Car.class);
                sb.append(
                        String.format("Successfully imported car - %s - %s%n",
                                carImportDto.getMake(),
                                carImportDto.getModel()));
                this.carRepository.saveAndFlush(mappedCar);
            }else {
                sb.append("Invalid car\n");
            }

        }

        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
