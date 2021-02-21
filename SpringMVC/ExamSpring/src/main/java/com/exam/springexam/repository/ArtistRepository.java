package com.exam.springexam.repository;

import com.exam.springexam.model.entity.ArtistEntity;
import com.exam.springexam.model.entity.enums.ArtistName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity,Long> {
    Optional<ArtistEntity> findByName(ArtistName artist);
}
