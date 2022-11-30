package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Before you run the program check the root and password in application.properties files
        //After you add the database run one by one tasks below

        seedData();
        Scanner scanner = new Scanner(System.in);

        //Run task 1. Books Titles by Age Restriction
//        String restriction = scanner.nextLine();
//        this.bookService.findAllTitleByAgeRestriction(restriction)
//                .forEach(System.out::println);

        //Run task 2. Golden Books
//        this.bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000)
//                .forEach(System.out::println);

        //Run task 3. Books by Price
//        this.bookService.findByPriceLessThanOrPriceGreaterThan(5,40)
//                .forEach(b-> System.out.println(b.getTitle() + " - $" + b.getPrice()));

        //Run task 4. Not Released Books
//        int year = Integer.parseInt(scanner.nextLine());
//        this.bookService.findNotReleasedIn(year)
//                .forEach(b-> System.out.println(b.getTitle()));

        //Run task 5. Books Released Before Date
//        String date = scanner.nextLine();
//        this.bookService.findReleasedDateBefore(date)
//                .forEach(b-> System.out.printf("%s %s %.2f%n",b.getTitle(),b.getEditionType(),b.getPrice()));

        //Run task 6. Authors Search
//        String endWith = scanner.nextLine();
//        this.authorService.findAuthorFirstNameEndingWith(endWith)
//                .stream().map(a->a.getFirstName() + " " + a.getLastName())
//                .forEach(System.out::println);

        //Run task 7. Books Search
//        String pattern = scanner.nextLine();
//        this.bookService.findAllTitleContainingPattern(pattern)
//                .stream().map(Book::getTitle)
//                .forEach(System.out::println);

        //Run task 8. Book Titles Search
//        String lastName = scanner.nextLine();
//        this.bookService.findAllAuthorsLastNameStartWith(lastName)
//                .forEach(b-> System.out.printf("%s (%s %s)%n",
//                        b.getTitle(),b.getAuthor().getFirstName(),b.getAuthor().getLastName()));


        //Run task 9. Count Books
//        int inputNumber = Integer.parseInt(scanner.nextLine());
//        int outputNumber = this.bookService.countTitleLongerThan(inputNumber);
//        System.out.println(outputNumber);
//        System.out.printf("There are %d books with longer title than %d symbols",outputNumber,inputNumber);


        //Run task 10. Total Book Copies
//        this.authorService.getWithTotalCopies()
//            .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " - " + a.getTotalCopies()));


        //Run task 11. Reduced Book
//        String title = scanner.nextLine();
//        BookSummary summary = this.bookService.getInformationForTitle(title);
//        System.out.println(summary.getTitle() + " " + summary.getEditionType() + " " + summary.getAgeRestriction() + " " + summary.getPrice());



        //printAllBooksAfterYear(2000);
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //printAllAuthorsAndNumberOfTheirBooks();
        //pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
