package com.advance_academy_project.bookstore.dto;

import com.advance_academy_project.bookstore.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private Integer age;

    private String role;

    private Set<Book> books;
}
