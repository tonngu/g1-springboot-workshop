package se.lexicon.g1.g1springbootworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;
import se.lexicon.g1.g1springbootworkshop.entity.Book;
import se.lexicon.g1.g1springbootworkshop.entity.BookLoan;
import se.lexicon.g1.g1springbootworkshop.entity.Details;
import se.lexicon.g1.g1springbootworkshop.repository.AppUserRepository;
import se.lexicon.g1.g1springbootworkshop.repository.BookLoanRepository;
import se.lexicon.g1.g1springbootworkshop.repository.BookRepository;
import se.lexicon.g1.g1springbootworkshop.repository.DetailsRepository;

import java.time.LocalDate;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookLoanRepository bookLoanRepository;


    @Override
    public void run(String... args) throws Exception {
        Book testBook = new Book("TestTitle","TestISBN",90);
        Details testDetails = new Details("testemail@test.com", "Test Testsson", LocalDate.of(2000, 1, 1));
        AppUser testUser = new AppUser("Testusername", "Password", testDetails);

        BookLoan testBookLoan = new BookLoan(testUser, testBook);
        BookLoan savedBookLoan = bookLoanRepository.save(testBookLoan);


    }
}
