package com.example.bookshop.services.authorService;

import com.example.bookshop.entities.Author;
import com.example.bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(
            AuthorRepository authorRepository
    ) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        // 10 -> [0..9] -> [1..10]
        int authorId = new Random().nextInt((int) size) + 1;

        return this.authorRepository.findById(authorId).get();
    }
}
