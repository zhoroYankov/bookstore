package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.UserSignUpDto;
import com.advance_academy_project.bookstore.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserSignUpConverter {

    public User convertToEntity(UserSignUpDto userSignUpDto){
        User user =  User.builder()
                .id(userSignUpDto.getId())
                .email(userSignUpDto.getEmail())
                .username(userSignUpDto.getUsername())
                .firstName(userSignUpDto.getFirstName())
                .lastName(userSignUpDto.getLastName())
                .age(userSignUpDto.getAge())
                .build();
        return user;
    }


}
