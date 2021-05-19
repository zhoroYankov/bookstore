package com.advance_academy_project.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    @Min(value = 14, message = "Sorry! You should be over 14 years old!")
    @Max(value = 70, message = "You don`t need account!")
    private Integer age;

    @Column(unique = true, nullable = false)
    private String emailAdress;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private LocalDateTime accountCreatedAt;

    @JsonManagedReference
    @OneToMany
    @Column(name = "book_id")
    private Set<Book> takenBooks;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
