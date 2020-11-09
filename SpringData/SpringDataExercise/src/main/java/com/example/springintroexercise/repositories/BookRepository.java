package com.example.springintroexercise.repositories;

import com.example.springintroexercise.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByReleaseDateBefore(LocalDate  localDate);

    List<Book> findAllByAuthorIdOrderByReleaseDateDescTitleAsc(long id);
}
