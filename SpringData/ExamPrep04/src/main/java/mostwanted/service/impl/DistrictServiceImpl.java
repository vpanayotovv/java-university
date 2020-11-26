package mostwanted.service.impl;

import mostwanted.service.DistrictService;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/districts.json";


    @Override
    public Boolean districtsAreImported() {
        //TODO: Implement me
        return false;
    }

    @Override
    public String readDistrictsJsonFile() {
        //TODO: Implement me
        return null;
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        //TODO: Implement me
        return null;
    }
}
