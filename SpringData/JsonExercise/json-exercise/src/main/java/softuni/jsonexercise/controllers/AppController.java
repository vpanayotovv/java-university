package softuni.jsonexercise.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.jsonexercise.servicies.PartService;
import softuni.jsonexercise.servicies.SupplierService;

@Component
public class AppController implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;

    public AppController(SupplierService supplierService, PartService partService) {
        this.supplierService = supplierService;
        this.partService = partService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.supplierService.seedData();
        this.partService.seedData();
    }
}
