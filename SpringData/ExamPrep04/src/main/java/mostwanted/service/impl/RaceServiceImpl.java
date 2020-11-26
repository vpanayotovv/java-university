package mostwanted.service.impl;

import mostwanted.service.RaceService;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class RaceServiceImpl implements RaceService {

    private final static String RACES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/races.xml";


    @Override
    public Boolean racesAreImported() {
        //TODO: Implement me
        return false;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        //TODO: Implement me
        return null;
    }

    @Override
    public String importRaces() throws JAXBException {
        //TODO: Implement me
        return null;
    }
}