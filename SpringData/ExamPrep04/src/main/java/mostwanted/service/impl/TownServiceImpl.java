package mostwanted.service.impl;

import mostwanted.service.TownService;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private final static String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/towns.json";

    @Override
    public Boolean townsAreImported() {
        //TODO: Implement me
        return false;
    }

    @Override
    public String readTownsJsonFile() {
        //TODO: Implement me
        return null;
    }

    @Override
    public String importTowns(String townsFileContent) {
        //TODO: Implement me
        return null;
    }

    @Override
    public String exportRacingTowns() {
        //TODO: Implement me
        return null;
    }
}
