package com.gira.giraexamprep.service;

import com.gira.giraexamprep.model.entity.ClassificationEntity;
import com.gira.giraexamprep.model.entity.enums.ClassificationName;
import org.springframework.stereotype.Service;

@Service
public interface ClassificationService {
    ClassificationEntity getByName(ClassificationName classificationName);

    void seedData();

}
