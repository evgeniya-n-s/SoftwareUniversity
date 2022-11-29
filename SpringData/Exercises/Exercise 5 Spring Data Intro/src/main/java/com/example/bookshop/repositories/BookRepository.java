package com.example.bookshop.repositories;

import com.example.bookshop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //query task 1
    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    //countBooks
    int countByReleaseDateAfter(LocalDate releaseDate);

    //query task 4
    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
