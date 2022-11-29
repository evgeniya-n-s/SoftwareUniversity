package com.example.bookshop.repositories;

import com.example.bookshop.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    //query task 2
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

}
