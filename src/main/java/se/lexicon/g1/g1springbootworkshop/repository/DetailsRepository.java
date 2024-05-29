package se.lexicon.g1.g1springbootworkshop.repository;

import org.springframework.stereotype.Repository;
import se.lexicon.g1.g1springbootworkshop.entity.Details;

import java.util.Optional;

@Repository
public interface DetailsRepository {
Optional<Details> findByEmailIgnoreCase(String email);
Optional<Details> findByNameContaining(String name);
Optional<Details> findByNameIgnoreCase(String name);
//Todo: add more methods as needed
}
