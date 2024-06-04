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
        detailsRepository.save(testDetails);
        appUserRepository.save(testUser);

        //2.Act
        List<AppUser> foundUsers = appUserRepository.findByUserDetails_NameIgnoreCase("test testsson");

        //3.Assert
        Assertions.assertNotNull(foundUsers);
        Assertions.assertFalse(foundUsers.isEmpty(), "Expected to find at least one user");
        Assertions.assertTrue(foundUsers.stream().anyMatch(user ->
                user.getUserDetails().getName().equalsIgnoreCase(testUser.getUserDetails().getName())
        ), "Expected to find a user with the specified name, ignoring case");
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
        List<AppUser> foundUsers = appUserRepository.findByUserDetails_Id(savedDetails.getId());

        //3.Assert
        Assertions.assertNotNull(foundUsers);
        Assertions.assertTrue(foundUsers.stream().allMatch(user ->
                user.getUserDetails().getId().equals(savedDetails.getId())
        ), "All found users should have the specified Details ID");
    }

    @Test
    @Transactional
    public void testFindByRegDateBetween() {
        //1.Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        detailsRepository.save(testDetails);
        appUserRepository.save(testUser);

        //2.Act
        List<AppUser> foundUsers = appUserRepository.findByRegDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));

        //3.Assert
        Assertions.assertNotNull(foundUsers);
        Assertions.assertFalse(foundUsers.isEmpty(), "Expected to find at least one user");
        Assertions.assertTrue(foundUsers.stream().allMatch(user ->
                user.getRegDate().isAfter(LocalDate.now().minusDays(1)) &&
                        user.getRegDate().isBefore(LocalDate.now().plusDays(1))
        ), "All found users should have registration dates within the specified range");
    }

}

