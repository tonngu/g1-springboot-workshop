package se.lexicon.g1.g1springbootworkshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column
    private LocalDate loanDate;

    @Column
    @Setter
    private LocalDate dueDate;

    @Setter
    @Column
    private boolean returned;

    @OneToMany(mappedBy = "bookLoan")
    private List<AppUser> appUsers;

    @OneToMany(mappedBy = "bookLoan")
    private List<Book> books;


}
