package com.advance_academy_project.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User{

    public enum role{CLIENT, VIP, ADMIN}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String password;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String firstName;

    @Column(unique = true)
    private String lastName;

    @Column(nullable = false)
    @Min(value = 16, message = "Sorry! You should be over 16 years old!")
    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private role type;

    @ManyToMany
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private Set<Book> books;

}
