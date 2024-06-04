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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookLoan(AppUser appUser, Book book){
        if (this.returned = false) throw new IllegalArgumentException("BOOK NOT AVAILABLE REEEEE");
        this.appUser = appUser;
        this.returned = false;
        this.loanDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(book.getMaxLoanDays());

    }


}
