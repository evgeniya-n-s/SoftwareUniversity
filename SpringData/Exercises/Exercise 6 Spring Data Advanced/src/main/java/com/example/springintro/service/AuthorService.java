package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    //06
    List<Author> findAuthorFirstNameEndingWith(String input);

    //10
    List<AuthorNamesWithTotalCopies> getWithTotalCopies();
}
