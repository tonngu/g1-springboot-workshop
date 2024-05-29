package se.lexicon.g1.g1springbootworkshop.repository;

import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository {
Optional<AppUser> findByUsername(String username);
List<AppUser> findByRegDateBetween(LocalDate start, LocalDate end);
Optional<AppUser> findByDetails_id(Long details_id);
Optional<AppUser> findByDetails_EmailIgnoreCase(String email);
    //Todo add more methods as needed
}
