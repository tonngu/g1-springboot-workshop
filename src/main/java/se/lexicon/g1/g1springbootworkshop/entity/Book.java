package se.lexicon.g1.g1springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @Column
    @Setter
    private boolean available = true;

    @ManyToMany(mappedBy = "writtenBooks")
    private Set<Author> authors;

    public Book(String title, String isbn, int MaxLoanDays) {
        this.title = title;
        this.isbn = isbn;
        this.maxLoanDays = MaxLoanDays;
    }

    public Book(String title, String isbn, int MaxLoanDays, Set<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.maxLoanDays = MaxLoanDays;
        this.authors = authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
        author.addWrittenBook(this);
    }

    public void removeAuthor(Author author){
        author.removeWrittenBook(this);
        authors.remove(author);
    }

}
