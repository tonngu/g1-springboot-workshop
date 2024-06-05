package se.lexicon.g1.g1springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Setter
    private String firstName;

    @Column
    @Setter
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> writtenBooks;

    public void addWrittenBook(Book book) {
        writtenBooks.add(book);
        book.addAuthor(this);
    }

    public void removeWrittenBook(Book book) {
        book.removeAuthor(this);
        writtenBooks.remove(book);
    }


}
