package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    //05
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);


    //01
    @Query("SELECT b.title FROM Book AS b WHERE b.ageRestriction = ageRestriction")
    List<String> findAllTitleByAgeRestriction(@Param("ageRestriction") AgeRestriction restriction);

    //02
    List<Book> findByEditionTypeAndCopiesLessThan (EditionType type, int copies);

    //03
    List<Book> findByPriceLessThanOrPriceGreaterThan (BigDecimal lowerPrice,BigDecimal higherPrice);

    //04
    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);


    //07
    List<Book> findByTitleContaining(String endWith);

    //08
    List<Book> findByAuthorLastNameStartingWith(String lastName);

    //09
    @Query("SELECT COUNT(b) FROM Book AS b WHERE length(b.title) > :inputNumber")
    int countBooksWithTitleLongerThan (@Param("inputNumber")int length);


    //11
    @Query("SELECT b.title AS title," +
            " b.editionType AS editionType," +
            " b.ageRestriction AS ageRestriction," +
            " b.price AS price" +
            " FROM Book b" +
            " WHERE b.title = :title")
    BookSummary findSummaryForTitle(String title);
}
