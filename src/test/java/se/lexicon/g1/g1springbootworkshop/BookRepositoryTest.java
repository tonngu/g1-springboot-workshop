package se.lexicon.g1.g1springbootworkshop;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;
import se.lexicon.g1.g1springbootworkshop.entity.Book;
import se.lexicon.g1.g1springbootworkshop.entity.BookLoan;
import se.lexicon.g1.g1springbootworkshop.entity.Details;
import se.lexicon.g1.g1springbootworkshop.repository.AppUserRepository;
import se.lexicon.g1.g1springbootworkshop.repository.BookLoanRepository;
import se.lexicon.g1.g1springbootworkshop.repository.BookRepository;
import se.lexicon.g1.g1springbootworkshop.repository.DetailsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookLoanRepository bookLoanRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        //1. Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);

        Book testBook = new Book("TestTitle","TestISBN",90);
        Book overdueBook = new Book("OverdueTitle","OverdueISBN",90);

        BookLoan testBookLoan = new BookLoan(testUser, testBook);
        bookLoanRepository.save(testBookLoan);

        BookLoan testBookLoan2 = new BookLoan(testUser, overdueBook);
        testBookLoan2.setDueDate(LocalDate.now().minusDays(1));
        bookLoanRepository.save(testBookLoan2);


    }

    @Test
    @Transactional
    public void testFindByOnLoan() {

        //2. Act
        List<Book> foundBooks = bookRepository.findByOnLoan();

        //3. Assert
        Assertions.assertNotNull(foundBooks);
        Assertions.assertFalse(foundBooks.isEmpty(), "Expected to find at least one book");
        Assertions.assertTrue(foundBooks.stream().anyMatch(book ->
                book.getTitle().equals("TestTitle")), "Expected to find the book with title 'TestTitle'");
    }

    @Test
    @Transactional
    public void testFindByOverdue() {
        //2. Act
        List<Book> foundBooks = bookRepository.findByOverdue();

        //3. Assert
        Assertions.assertNotNull(foundBooks);
        Assertions.assertFalse(foundBooks.isEmpty(), "Expected to find at least one book");
        Assertions.assertTrue(foundBooks.stream().anyMatch(book ->
                book.getTitle().equals("OverdueTitle")), "Expected to find the book with title 'OverdueTitle'");
    }

    @Test
    @Transactional
    public void testFindByDateBetween() {

        //2. Act
        List<Book> foundBooks = bookRepository.findByDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));

        //3. Assert
        Assertions.assertNotNull(foundBooks);
        Assertions.assertFalse(foundBooks.isEmpty(), "Expected to find at least one book");
        Assertions.assertTrue(foundBooks.stream().anyMatch(book ->
                book.getTitle().equals("OverdueTitle")), "Expected to find the book with title 'OverdueTitle'");
//Optional only expects 0 or 1 results, need to rewrite methods into List
    }



}