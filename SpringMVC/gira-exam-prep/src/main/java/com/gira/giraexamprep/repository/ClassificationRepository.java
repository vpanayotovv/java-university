package com.gira.giraexamprep.repository;

import com.gira.giraexamprep.model.entity.ClassificationEntity;
import com.gira.giraexamprep.model.entity.enums.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationEntity, Long> {

    Optional<ClassificationEntity> findByClassificationName(ClassificationName classificationName);

}
