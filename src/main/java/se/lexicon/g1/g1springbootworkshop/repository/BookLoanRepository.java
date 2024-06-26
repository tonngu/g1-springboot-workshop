package se.lexicon.g1.g1springbootworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    List<BookLoan> findByBorrower_Id(Long appUserId);

    List<BookLoan> findByBook_Id(Long bookId);

    List<BookLoan> findByReturnedFalse();



    @Query("select bl from BookLoan bl where bl.returned = false AND bl.dueDate < NOW()")
    List<BookLoan> findOverdueBookLoan();

    List<BookLoan> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query("Update BookLoan bl Set bl.returned = true WHERE bl.id = :BookLoanId")
    void updateReturned(@Param("BookLoanId") Long BookLoanId);
}
