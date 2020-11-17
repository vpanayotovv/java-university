package softuni.jsonexercise.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.jsonexercise.servicies.SupplierService;

@Component
public class AppController implements CommandLineRunner {

    private final SupplierService supplierService;

    public AppController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedData();
    }
}
