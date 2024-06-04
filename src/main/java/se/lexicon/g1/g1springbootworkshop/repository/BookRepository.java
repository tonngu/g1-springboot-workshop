package se.lexicon.g1.g1springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.Book;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findByIsbnIgnoreCase(String isbn);

    Optional<Book> findByTitleContaining(String title);

    Optional<Book> findByMaxLoanDaysLessThan(int maxLoanDays);

    //Find books that are currently on loan
    @Query("select b from BookLoan bl join Book b where bl.returned = false")
    Optional<Book> findByOnLoan();

    //Find books that are overdue
    @Query("select b from BookLoan bl join Book b where bl.returned = false AND bl.dueDate < NOW()")
    Optional<Book> findByOverdue();

    //Find books loaned between specific dates
    @Query("select b from BookLoan bl join Book b where bl.loanDate >= :startDate AND bl.loanDate <= :endDate")
    Optional<Book> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
