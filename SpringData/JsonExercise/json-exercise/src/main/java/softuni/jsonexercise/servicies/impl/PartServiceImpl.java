package softuni.jsonexercise.servicies.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.dtos.PartSeedDto;
import softuni.jsonexercise.domain.entities.Constants;
import softuni.jsonexercise.domain.entities.Part;
import softuni.jsonexercise.domain.entities.Supplier;
import softuni.jsonexercise.repositories.PartRepository;
import softuni.jsonexercise.servicies.PartService;
import softuni.jsonexercise.servicies.SupplierService;
import softuni.jsonexercise.utils.CustomFileReader;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CustomFileReader reader;
    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson, CustomFileReader reader, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.reader = reader;
        this.supplierService = supplierService;
    }

    @Override
    public void seedData() throws Exception {

        String read = String.join("", reader.read(Constants.PARTS_PATH));

        PartSeedDto[] partSeedDtos = this.gson.fromJson(read, PartSeedDto[].class);


        for (PartSeedDto partSeedDto : partSeedDtos) {
            Part part = this.modelMapper.map(partSeedDto,Part.class);
            part.setSupplier(getRandomSupplier());
            this.partRepository.saveAndFlush(part);
        }

    }

    @Override
    public long getCount() {
        return this.partRepository.count();
    }

    @Override
    public Part getPartById(Long id) throws Exception {
        return this.partRepository.findById(id).orElseThrow( () ->new Exception("No such Part!"));
    }

    private Supplier getRandomSupplier() throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long index = random.nextInt(this.supplierService.getCount());
        return this.supplierService.getById(index + 1);
    }
}
