package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.TownSeedDto;
import hiberspring.domain.entity.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder result = new StringBuilder();

        TownSeedDto[] townSeedDtos = this.gson.fromJson(readTownsJsonFile(), TownSeedDto[].class);

        for (TownSeedDto townSeedDto : townSeedDtos) {
            if (this.validationUtil.isValid(townSeedDto)){
                if (this.townRepository.findByName(townSeedDto.getName()) == null){
                    Town mappedTown = this.modelMapper.map(townSeedDto, Town.class);
                    this.townRepository.saveAndFlush(mappedTown);
                    result.append(String.format("Successfully imported Town %s",mappedTown.getName()))
                            .append(System.lineSeparator());

                }else {
                    result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                }
            }
            else {
                result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }
        }


        return result.toString();
    }
}
