package se.lexicon.g1.g1springbootworkshop;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
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
public class AppUserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookLoanRepository bookLoanRepository;

    @Test
    @Transactional
    public void testSaveAndFindById() {
        //1.Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        //2.Act
        AppUser savedUser = appUserRepository.save(testUser);
        //3.Assert
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());

        Optional<AppUser> foundUser = appUserRepository.findById(savedUser.getId());
        Assertions.assertTrue(foundUser.isPresent());
    }

    @Test
    @Transactional
    public void testFindByNameIgnoreCase() {
        //1.Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        Details savedDetails = detailsRepository.save(testDetails);
        AppUser savedUser = appUserRepository.save(testUser);

        //2.Act
        Optional<AppUser> foundUser = appUserRepository.findByUserDetails_NameIgnoreCase("test testsson");

        //3.Assert
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(testUser.getUserDetails().getName(), foundUser.get().getUserDetails().getName());
    }

    @Test
    @Transactional
    public void testFindByUserDetails_Id() {
        //1.Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        Details savedDetails = detailsRepository.save(testDetails);
        AppUser savedUser = appUserRepository.save(testUser);

        //2.Act
        Optional<AppUser> foundUser = appUserRepository.findByUserDetails_Id(savedDetails.getId());

        //3.Assert
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(savedDetails.getId(), foundUser.get().getUserDetails().getId());
    }

    @Test
    @Transactional
    public void testFindByRegDateBetween() {
        //1.Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        Details savedDetails = detailsRepository.save(testDetails);
        AppUser savedUser = appUserRepository.save(testUser);

        //2.Act
        Optional<AppUser> foundUserOptional = appUserRepository.findByRegDateBetween(LocalDate.of(2024, 5, 29), LocalDate.of(2024, 6, 1));

        //3.Assert
        Assertions.assertNotNull(foundUserOptional);
        Assertions.assertTrue(foundUserOptional.get().getRegDate().isAfter(LocalDate.now().minusDays(1)));
        Assertions.assertTrue(foundUserOptional.get().getRegDate().isBefore(LocalDate.now().plusDays(1)));
    }

    @Test
    @Transactional
    public void testFindByOnLoan() {
        //1. Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        Book testBook = new Book("TestTitle","TestISBN",90);
        BookLoan testBookLoan = new BookLoan(testUser, testBook);
        AppUser savedUser = appUserRepository.save(testUser);
        Book savedBook = bookRepository.save(testBook);
        BookLoan savedBookLoan = bookLoanRepository.save(testBookLoan);

        //2. Act
        Optional<Book> bookOptional = bookRepository.findByOnLoan();

        //3. Assert
        Assertions.assertNotNull(bookOptional);


    }
}

