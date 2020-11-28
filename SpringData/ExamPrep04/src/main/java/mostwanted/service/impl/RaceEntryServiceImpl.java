package mostwanted.service.impl;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntryImportDto;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.service.RaceEntryService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private final RaceEntryRepository raceEntryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final CarRepository carRepository;

    private final static String RACE_ENTRIES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, RacerRepository racerRepository, FileUtil fileUtil, CarRepository carRepository) {
        this.raceEntryRepository = raceEntryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.carRepository = carRepository;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRIES_XML_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws JAXBException {
        StringBuilder result = new StringBuilder();

        RaceEntryImportRootDto raceEntryImportRootDto = this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_XML_FILE_PATH);

        int counter = 0;
        for (RaceEntryImportDto raceEntry : raceEntryImportRootDto.getRaceEntries()) {
            RaceEntry mappedRaceEntry = this.modelMapper.map(raceEntry, RaceEntry.class);
            Optional<Car> car = this.carRepository.findById(raceEntry.getCar());
            Racer racer = this.racerRepository.findByName(raceEntry.getRacer());
            if (car.isPresent() && racer != null) {
                counter++;
                mappedRaceEntry.setCar(car.get());
                mappedRaceEntry.setRacer(racer);


                this.raceEntryRepository.saveAndFlush(mappedRaceEntry);
                result.append(String.format(
                        "Successfully imported RaceEntry - %d.",
                        counter)
                ).append(System.lineSeparator());


            }else {
                result.append(
                        Constants.INCORRECT_DATA_MESSAGE
                ).append(System.lineSeparator());
            }
        }


        return result.toString();
    }
}
