package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.service.RacerService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RacerServiceImpl implements RacerService {
    private final RacerRepository racerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    public RacerServiceImpl(RacerRepository racerRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, TownRepository townRepository, FileUtil fileUtil) {
        this.racerRepository = racerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) throws IOException {
        StringBuilder result = new StringBuilder();

        RacerImportDto[] racerImportDtos = this.gson.fromJson(readRacersJsonFile(), RacerImportDto[].class);
        for (RacerImportDto racer : racerImportDtos) {
            if (this.validationUtil.isValid(racer)){
                if (this.racerRepository.findByName(racer.getName()) == null){
                    Racer mappedRacer = this.modelMapper.map(racer, Racer.class);
                    Town town = this.townRepository.findByName(racer.getHomeTown());
                    mappedRacer.setTown(town);
                    this.racerRepository.saveAndFlush(mappedRacer);

                    result.append(String.format(
                            Constants.SUCCESSFUL_IMPORT_MESSAGE,
                            Racer.class.getSimpleName(),
                            mappedRacer.getName()
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

    @Override
    public String exportRacingCars() {
        //TODO: Implement me
        return null;
    }
}
