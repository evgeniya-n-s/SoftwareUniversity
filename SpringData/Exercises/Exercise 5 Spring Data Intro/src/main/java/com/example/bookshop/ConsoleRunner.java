package com.example.bookshop;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.seedServices.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final String FIRST_NAME_AUTHOR = "George";
    private final String LAST_NAME_AUTHOR = "Powell";

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(
            SeedService seedService,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //Before you run the program check the root and password in application.properties files
        //After you add the database run one by one queries below
        this.seedService.seedAll();

        //run query 1: Get all books after the year 2000. Print only their titles.
//        this._01_booksAfter2000();

        //run query 2: Get all authors with at least one book with release date before 1990. Print their first name and last name.
//        this._02_allAuthorsWithBookBefore1990();

        //run query3: Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.
//        this._03_allAuthorsOrderedByBookCount();

        //run query4: Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.
//        this._04_allBooksAuthor();
    }


    private void _01_booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));

        //if you want to see the count of books:
//        int count = this.bookRepository.countByReleaseDateAfter(year2000);
//        System.out.println("Total count: " + count);
    }

    private void _02_allAuthorsWithBookBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author ->
                        System.out.printf("%s %s -> %d%n",
                                author.getFirstName(),
                                author.getLastName(),
                                author.getBooks().size()));
    }

    private void _04_allBooksAuthor() {
        List<Book> books = this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(FIRST_NAME_AUTHOR, LAST_NAME_AUTHOR);

        books.forEach(book -> System.out.println(book.getTitle() + " "
                + book.getReleaseDate() + " "
                + book.getCopies()));
    }
}
