package se.lexicon.g1.g1springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByRegDateBetween(LocalDate start, LocalDate end);

    Optional<AppUser> findByUserDetails_Id(Long details_id);

    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);

    //Todo add more methods as needed
    Optional<AppUser> findByUserDetails_NameIgnoreCase(String name);
}
