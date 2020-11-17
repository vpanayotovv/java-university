package softuni.jsonexercise.servicies;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.dtos.SupplierSeedDto;
import softuni.jsonexercise.domain.entities.Constants;
import softuni.jsonexercise.domain.entities.Supplier;
import softuni.jsonexercise.repositories.SupplierRepository;
import softuni.jsonexercise.utils.CustomFileReader;

import java.io.IOException;
import java.util.NoSuchElementException;


@Service
public class SupplierServiceImpl implements SupplierService {

    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final CustomFileReader reader;

    @Autowired
    public SupplierServiceImpl(ModelMapper modelMapper, SupplierRepository supplierRepository, Gson gson, CustomFileReader reader) {
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.reader = reader;
    }

    @Override
    public void seedData() throws IOException {

        String read = String.join("", reader.read(Constants.SUPPLIERS_PATH));

        SupplierSeedDto[] supplierSeedDto = this.gson.fromJson(read,SupplierSeedDto[].class);

        for (SupplierSeedDto seedDto : supplierSeedDto) {
            this.supplierRepository.saveAndFlush(this.modelMapper.map(seedDto,Supplier.class));
        }

    }

    @Override
    public int getCount() {
        return (int) this.supplierRepository.count();
    }

    @Override
    public Supplier getById(Long id) {
       return this.supplierRepository.findById(id).orElseThrow(() -> new NoSuchElementException("bal bal"));
    }
}
