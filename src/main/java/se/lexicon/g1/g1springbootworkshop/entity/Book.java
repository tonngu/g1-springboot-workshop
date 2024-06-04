package se.lexicon.g1.g1springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Setter
    private String isbn;

    @Column
    @Setter
    private String title;

    @Column(nullable = false)
    @Setter
    private int maxLoanDays;

    public Book(String title, String isbn, int MaxLoanDays) {
        this.title = title;
        this.isbn = isbn;
        this.maxLoanDays = MaxLoanDays;
    }



}
