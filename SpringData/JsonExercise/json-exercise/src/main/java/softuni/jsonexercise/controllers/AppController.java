package softuni.jsonexercise.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.jsonexercise.servicies.CarService;
import softuni.jsonexercise.servicies.CustomerService;
import softuni.jsonexercise.servicies.PartService;
import softuni.jsonexercise.servicies.SupplierService;

@Component
public class AppController implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;

    public AppController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedData();
        this.partService.seedData();
        this.carService.seedData();
        this.customerService.seedData();
    }
}
