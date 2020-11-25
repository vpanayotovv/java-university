package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.EmployeeCardSeedDto;
import hiberspring.domain.entity.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.EMPLOYEE_CARDS_FILE_PATH));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        EmployeeCardSeedDto[] employeeCardSeedDtos = this.gson.fromJson(new FileReader(GlobalConstants.EMPLOYEE_CARDS_FILE_PATH), EmployeeCardSeedDto[].class);

        for (EmployeeCardSeedDto employeeCardSeedDto : employeeCardSeedDtos) {
            if (this.validationUtil.isValid(employeeCardSeedDto)) {

                if (this.employeeCardRepository.findByNumber(employeeCardSeedDto.getNumber()) == null) {

                    EmployeeCard mappedCard = this.modelMapper.map(employeeCardSeedDto, EmployeeCard.class);
                    this.employeeCardRepository.saveAndFlush(mappedCard);

                    result.append("Successfully imported Employee Card ").append(employeeCardSeedDto.getNumber()).append(System.lineSeparator());

                } else {
                    result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                }

            } else {
                result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }
        }


        return result.toString();
    }
}
