package se.lexicon.g1.g1springbootworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    @Query("select a from Author a where concat(a.firstName,' ', a.lastName) like %:keyword%")
    List<Author> findByFirstNameAndLastNameContaining(@Param("keyword") String keyword);

    List<Author> findByWrittenBooks_Id(Long bookId);

    @Modifying
    @Transactional
    @Query("Update Author a Set a.firstName = :firstName, a.lastName = :lastName Where a.id = :id")
    void updateNameById(@Param("firstName")String firstName, @Param("lastName") String lastName, @Param("id") Long id);


}
