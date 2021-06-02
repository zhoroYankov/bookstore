package com.advance_academy_project.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDto {

    private Long id;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private Integer age;

}
