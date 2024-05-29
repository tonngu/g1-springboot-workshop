package se.lexicon.g1.g1springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100,unique = true)
    @Setter
    private String email;

    @Column(nullable = false, length = 100)
    @Setter
    private String name;

    @Setter
    @Column(nullable = false, length = 100)
    private LocalDate birthDate;

}
