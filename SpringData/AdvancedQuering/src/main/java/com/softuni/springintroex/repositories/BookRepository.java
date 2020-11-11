package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType,int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal minPrice,BigDecimal maxPrice);

    @Query("select b from Book b where substring(b.releaseDate,0,4) not like :year")
    List<Book> findBookNotReleasedInYear(String year);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByTitleContaining(String string);

    List<Book> findAllByAuthorLastNameStartingWith(String text);

    @Query("SELECT count(b) from Book b where length(b.title) > :length")
    int getCountBooksWithTitleIsLongerThen(int length);
}
