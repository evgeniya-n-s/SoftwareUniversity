package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);


    //01
    List<String> findAllTitleByAgeRestriction(String ageRestriction);

    //02
    List<String> findAllTitlesByEditionAndCopies(EditionType gold, int copies);

    //03
    List<Book> findByPriceLessThanOrPriceGreaterThan (float lowerPrice, float higherPrice);

    //04
    List<Book> findNotReleasedIn(int releaseYear);

    //05
    List<Book> findReleasedDateBefore(String date);

    //07
    List<Book> findAllTitleContainingPattern(String pattern);

    //08
    List<Book> findAllAuthorsLastNameStartWith(String lastName);

    //09
    int countTitleLongerThan(int inputNumber);

    //11
    BookSummary getInformationForTitle(String title);
}
