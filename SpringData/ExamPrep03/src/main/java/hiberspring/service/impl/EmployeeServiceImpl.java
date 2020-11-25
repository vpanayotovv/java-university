package hiberspring.service.impl;

import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.EmployeeSeedDto;
import hiberspring.domain.dto.EmployeeSeedRootDto;
import hiberspring.domain.entity.Branch;
import hiberspring.domain.entity.Employee;
import hiberspring.domain.entity.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.EMPLOYEES_FILE_PATH));
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {
        StringBuilder result = new StringBuilder();

        EmployeeSeedRootDto employeeSeedRootDto = this.xmlParser.parseXml(EmployeeSeedRootDto.class, GlobalConstants.EMPLOYEES_FILE_PATH);
        for (EmployeeSeedDto employee : employeeSeedRootDto.getEmployees()) {
            if (this.validationUtil.isValid(employee)){
                if (this.employeeRepository.findByLastName(employee.getLastName()) == null){
                    Employee mappedEmployee = this.modelMapper.map(employee, Employee.class);
                    EmployeeCard card = this.employeeCardRepository.findByNumber(employee.getCard());
                    Branch branch = this.branchRepository.findByName(employee.getBranch());

                    mappedEmployee.setBranch(branch);
                    mappedEmployee.setCard(card);

                    this.employeeRepository.saveAndFlush(mappedEmployee);

                    result.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE,mappedEmployee.getFirstName(),mappedEmployee.getLastName())).append(System.lineSeparator());

                }else {
                    result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                }

            }else {
               result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }
        }


        return result.toString();
    }

    @Override
    public String exportProductiveEmployees() {
        return null;
    }
}
