package mostwanted.service.impl;

import mostwanted.service.RaceEntryService;
import org.springframework.stereotype.Service;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final static String RACE_ENTRIES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    @Override
    public Boolean raceEntriesAreImported() {
        //TODO: Implement me
        return false;
    }

    @Override
    public String readRaceEntriesXmlFile() {
        //TODO: Implement me
        return null;
    }

    @Override
    public String importRaceEntries() {
        //TODO: Implement me
        return null;
    }
}
