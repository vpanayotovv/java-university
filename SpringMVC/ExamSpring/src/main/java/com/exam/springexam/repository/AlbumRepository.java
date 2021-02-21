package com.exam.springexam.repository;

import com.exam.springexam.model.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity,Long> {

    @Query("select sum(a.copies)from AlbumEntity a")
    Long getTotalCopies();

    @Query("select a from AlbumEntity a order by a.copies desc ")
    List<AlbumEntity> getAllSorted();

}
