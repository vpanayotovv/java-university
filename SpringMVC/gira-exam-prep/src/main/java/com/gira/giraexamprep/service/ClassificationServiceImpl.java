package com.gira.giraexamprep.service;

import com.gira.giraexamprep.model.entity.ClassificationEntity;
import com.gira.giraexamprep.model.entity.enums.ClassificationName;
import com.gira.giraexamprep.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public ClassificationEntity getByName(ClassificationName classificationName) {
        return classificationRepository.findByClassificationName(classificationName).orElse(null);
    }

    @Override
    public void seedData() {
        if (this.classificationRepository.count() == 0) {
            Arrays.stream(ClassificationName.values())
                    .forEach(category -> createClassification(category, String.format("This is the %S category", category.name())));
        }
    }

    private void createClassification(ClassificationName name, String description) {
        ClassificationEntity classificationEntity = new ClassificationEntity();
        classificationEntity.setClassificationName(name);
        classificationEntity.setDescription(description);
        this.classificationRepository.saveAndFlush(classificationEntity);
    }
}
