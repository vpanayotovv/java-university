package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.service.DistrictService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final TownRepository townRepository;

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/districts.json";

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, TownRepository townRepository) {
        this.districtRepository = districtRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
    }


    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) throws IOException {
        StringBuilder result = new StringBuilder();

        for (DistrictImportDto district : this.gson.fromJson(readDistrictsJsonFile(), DistrictImportDto[].class)) {
            if (this.validationUtil.isValid(district)) {
                if (this.districtRepository.findByName(district.getName()) == null) {
                    District mappedDistrict = this.modelMapper.map(district, District.class);

                    Town town = this.townRepository.findByName(district.getTownName());
                    mappedDistrict.setTown(town);
                    this.districtRepository.saveAndFlush(mappedDistrict);
                    result.append(String.format(
                            Constants.SUCCESSFUL_IMPORT_MESSAGE,
                            "District",
                            district.getName()
                    )).append(System.lineSeparator());


                } else {
                    result.append(
                            Constants.DUPLICATE_DATA_MESSAGE
                    ).append(System.lineSeparator());
                }

            } else {
                result.append(
                        Constants.INCORRECT_DATA_MESSAGE
                ).append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
