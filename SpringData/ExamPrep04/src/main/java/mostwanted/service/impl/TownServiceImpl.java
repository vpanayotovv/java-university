package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.service.TownService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    private final static String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/towns.json";

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder result = new StringBuilder();

        TownImportDto[] townImportDtos =
                this.gson.fromJson(this.fileUtil.readFile(TOWNS_JSON_FILE_PATH), TownImportDto[].class);

        for (TownImportDto townImportDto : townImportDtos) {
            if (this.validationUtil.isValid(townImportDto)){
                if (this.townRepository.findByName(townImportDto.getName()) == null){
                    Town mappedTown = this.modelMapper.map(townImportDto, Town.class);
                    this.townRepository.saveAndFlush(mappedTown);

                    result.append(
                            String.format(
                                    Constants.SUCCESSFUL_IMPORT_MESSAGE,
                                    Town.class.getSimpleName(),
                                    mappedTown.getName()
                            )).append(System.lineSeparator()
                    );
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
    public String exportRacingTowns() {
        //TODO: Implement me
        return null;
    }
}
