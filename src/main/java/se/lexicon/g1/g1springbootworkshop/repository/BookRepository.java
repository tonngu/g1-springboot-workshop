package se.lexicon.g1.g1springbootworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Transactional
    @Query("select b from BookLoan bl join Book b on bl.book.id = b.id where bl.returned = false")
    Optional<Book> findByOnLoan();

    //Find books that are overdue

    @Transactional
    @Query("select b from BookLoan bl join Book b on bl.book.id = b.id where bl.returned = false AND bl.dueDate < NOW()")
    Optional<Book> findByOverdue();

    //Find books loaned between specific dates

    @Transactional
    @Query("select b from BookLoan bl join Book b on bl.book.id = b.id where bl.loanDate >= :startDate AND bl.loanDate <= :endDate")
    Optional<Book> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
