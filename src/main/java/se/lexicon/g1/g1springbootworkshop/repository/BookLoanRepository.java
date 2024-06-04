package se.lexicon.g1.g1springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.BookLoan;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

}
