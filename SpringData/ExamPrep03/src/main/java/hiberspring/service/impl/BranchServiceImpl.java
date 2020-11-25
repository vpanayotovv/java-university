package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.BranchSeedDto;
import hiberspring.domain.entity.Branch;
import hiberspring.domain.entity.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
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
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }


    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.BRANCHES_FILE_PATH));
    }

    @Override
    public String importBranches(String branchesFileContent) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();


        BranchSeedDto[] branchSeedDtos = this.gson.fromJson(new FileReader(GlobalConstants.BRANCHES_FILE_PATH), BranchSeedDto[].class);

        for (BranchSeedDto branchSeedDto : branchSeedDtos) {
            if (this.validationUtil.isValid(branchSeedDto)){
                if (this.branchRepository.findByName(branchSeedDto.getName()) == null){

                    Branch mappedBranch = this.modelMapper.map(branchSeedDto, Branch.class);

                    Town town = this.townRepository.findByName(branchSeedDto.getTown());

                    mappedBranch.setTown(town);

                    this.branchRepository.saveAndFlush(mappedBranch);

                    result.append(String.format("Successfully imported Branch %s",mappedBranch.getName())).append(System.lineSeparator());

                }else {
                    result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                }
            }else {
                result.append(GlobalConstants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }
        }


        return result.toString();
    }
}
