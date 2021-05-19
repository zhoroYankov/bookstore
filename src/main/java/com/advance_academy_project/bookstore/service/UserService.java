package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.repository.BookRepository;
import com.advance_academy_project.bookstore.repository.RoleRepository;
import com.advance_academy_project.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserService {

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService (UserRepository userRepository, BookRepository bookRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.roleRepository = roleRepository;
    }


    public void save(@NotNull UserDto userDto){


    }




}
