package softuni.jsonexercise.servicies.impl;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.dtos.CustomerSeedDto;
import softuni.jsonexercise.domain.entities.Constants;
import softuni.jsonexercise.domain.entities.Customer;
import softuni.jsonexercise.repositories.CustomerRepository;
import softuni.jsonexercise.servicies.CustomerService;
import softuni.jsonexercise.utils.CustomFileReader;

import java.io.FileNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CustomFileReader reader;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson, CustomFileReader reader) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.reader = reader;
    }

    @Override
    public void seedData() throws FileNotFoundException {
        String read = String.join("", reader.read(Constants.CUSTOMERS_PATH));

        CustomerSeedDto[] customerSeedDto = this.gson.fromJson(read,CustomerSeedDto[].class);

        for (CustomerSeedDto seedDto : customerSeedDto) {
            Customer customer = this.modelMapper.map(seedDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }

    }

    @Override
    public Customer getById(Long id) {
        return this.customerRepository.getById(id);
    }
}
