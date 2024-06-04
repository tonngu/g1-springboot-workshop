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


    @Column
    @Setter
    private boolean returned;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appUser_id")
    private AppUser borrower;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookLoan(AppUser borrower, Book book){
        this.borrower = borrower;
        this.loanDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(book.getMaxLoanDays());
        this.book = book;
    }


}
