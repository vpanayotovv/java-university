package com.gira.giraexamprep.init;

import com.gira.giraexamprep.service.ClassificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final ClassificationService classificationService;

    public DbInit(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.classificationService.seedData();
    }
}
