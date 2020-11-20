package softuni.jsonexercise.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.entities.Sale;
import softuni.jsonexercise.repositories.SaleRepository;
import softuni.jsonexercise.servicies.CarService;
import softuni.jsonexercise.servicies.CustomerService;
import softuni.jsonexercise.servicies.SaleService;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
    }


    @Override
    public void seedData() {

        Sale sale = new Sale();
        sale.setDiscount(5);
        sale.setCar(this.carService.getCarById(ThreadLocalRandom.current().nextLong(5)));
        sale.setCustomer(this.customerService.getById(ThreadLocalRandom.current().nextLong(5)));

        Sale sale1 = new Sale();
        sale1.setDiscount(10);
        sale1.setCar(this.carService.getCarById(ThreadLocalRandom.current().nextLong(5)));
        sale1.setCustomer(this.customerService.getById(ThreadLocalRandom.current().nextLong(5)));

        Sale sale2 = new Sale();
        sale2.setDiscount(15);
        sale2.setCar(this.carService.getCarById(ThreadLocalRandom.current().nextLong(5)));
        sale2.setCustomer(this.customerService.getById(ThreadLocalRandom.current().nextLong(5)));

        this.saleRepository.saveAndFlush(sale);
        this.saleRepository.saveAndFlush(sale1);
        this.saleRepository.saveAndFlush(sale2);


    }
}
