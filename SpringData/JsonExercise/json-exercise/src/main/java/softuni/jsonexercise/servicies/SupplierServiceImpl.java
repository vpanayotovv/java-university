package softuni.jsonexercise.servicies;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.dtos.SupplierSeedDto;
import softuni.jsonexercise.domain.entities.Constants;
import softuni.jsonexercise.domain.entities.Supplier;
import softuni.jsonexercise.repositories.SupplierRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class SupplierServiceImpl implements SupplierService {

    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final Gson gson;

    @Autowired
    public SupplierServiceImpl(ModelMapper modelMapper, SupplierRepository supplierRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {

        String content = String.join("", Files.readAllLines(Path.of(Constants.SUPPLIERS_PATH)));

        SupplierSeedDto[] data = this.gson.fromJson(content, SupplierSeedDto[].class);

        for (SupplierSeedDto supplier : data) {
            Supplier supDto = this.modelMapper.map(supplier, Supplier.class);
            this.supplierRepository.saveAndFlush(supDto);
        }
    }
}
