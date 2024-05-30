package se.lexicon.g1.g1springbootworkshop;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;
import se.lexicon.g1.g1springbootworkshop.entity.Details;
import se.lexicon.g1.g1springbootworkshop.repository.AppUserRepository;
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
       /* //1.Arrange
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        AppUser savedUser = appUserRepository.save(testUser);

        //2.Act
        Optional<AppUser> foundUser = appUserRepository.findByUserDetails_NameIgnoreCase("testusername");

        //3.Assert
        //Assertions.assertNotNull(foundUser);
        //Assertions.assertEquals(testUser.getUserDetails().getName(), foundUser.get().getUserDetails().getName());*/
    }


}

