package mostwanted.service.impl;

import mostwanted.service.RacerService;
import org.springframework.stereotype.Service;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    @Override
    public Boolean racersAreImported() {
        //TODO: Implement me
        return null;
    }

    @Override
    public String readRacersJsonFile() {
        //TODO: Implement me
        return null;
    }

    @Override
    public String importRacers(String racersFileContent) {
        //TODO: Implement me
        return null;
    }

    @Override
    public String exportRacingCars() {
        //TODO: Implement me
        return null;
    }
}
