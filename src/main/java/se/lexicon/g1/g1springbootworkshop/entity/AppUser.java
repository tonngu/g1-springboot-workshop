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
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    @Setter
    private String username;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column
    private LocalDate regDate;


    @OneToOne
    @JoinColumn(name = "details_id")
    @Setter private Details userDetails;


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();

    }

    public AppUser(String username, String password, Details userDetails) {
        this(username, password);
        this.regDate = LocalDate.now();
        this.userDetails = userDetails;
    }


}
