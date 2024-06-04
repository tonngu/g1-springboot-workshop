package se.lexicon.g1.g1springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByUsername(String username);

    List<AppUser> findByRegDateBetween(LocalDate start, LocalDate end);

   List<AppUser> findByUserDetails_Id(Long details_id);

    List<AppUser> findByUserDetails_EmailIgnoreCase(String email);

    //Todo add more methods as needed
    List<AppUser> findByUserDetails_NameIgnoreCase(String name);
}
