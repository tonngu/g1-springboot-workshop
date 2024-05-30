package se.lexicon.g1.g1springbootworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;
import se.lexicon.g1.g1springbootworkshop.entity.Details;
import se.lexicon.g1.g1springbootworkshop.repository.AppUserRepository;
import se.lexicon.g1.g1springbootworkshop.repository.DetailsRepository;

import java.time.LocalDate;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;


    @Override
    public void run(String... args) throws Exception {
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);
        detailsRepository.save(testDetails);
        appUserRepository.save(testUser);



    }
}
