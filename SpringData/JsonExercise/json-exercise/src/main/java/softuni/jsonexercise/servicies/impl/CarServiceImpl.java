package softuni.jsonexercise.servicies.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.dtos.CarSeedDto;
import softuni.jsonexercise.domain.entities.Car;
import softuni.jsonexercise.domain.entities.Constants;
import softuni.jsonexercise.domain.entities.Part;
import softuni.jsonexercise.repositories.CarRepository;
import softuni.jsonexercise.servicies.CarService;
import softuni.jsonexercise.servicies.PartService;
import softuni.jsonexercise.utils.CustomFileReader;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final PartService partService;
    private final CustomFileReader reader;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, PartService partService, CustomFileReader reader) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.partService = partService;
        this.reader = reader;
    }

    @Override
    public void seedData() throws Exception {

        String read = String.join("", reader.read(Constants.CARS_PATH));

        CarSeedDto[] carSeedDto = this.gson.fromJson(read,CarSeedDto[].class);

        for (CarSeedDto seedDto : carSeedDto) {
            Car car = this.modelMapper.map(seedDto, Car.class);
            car.setParts(getRandomParts());
            this.carRepository.saveAndFlush(car);
        }

    }

    private List<Part> getRandomParts() throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long count = this.partService.getCount();
        List<Part> parts = new LinkedList<>();
        if (count != 0) {
            long index = random.nextInt((int) count);
            for (int i = 0; i < 10; i++) {
                Part partById = this.partService.getPartById(index+1);
                parts.add(partById);
            }
        }
        return parts;
    }
}
