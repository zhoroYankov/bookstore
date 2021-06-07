package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.converter.BookConverter;
import com.advance_academy_project.bookstore.converter.UserFullConverter;
import com.advance_academy_project.bookstore.converter.UserSignUpConverter;
import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.dto.UserSignUpDto;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.model.User;
import com.advance_academy_project.bookstore.service.BookService;
import com.advance_academy_project.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;
    private UserFullConverter userFullConverter;
    private UserSignUpConverter userSignUpConverter;
    private BookConverter bookConverter;

    @Autowired
    public UserController(UserService userService,
                          UserFullConverter userFullConverter,
                          UserSignUpConverter userSignUpConverter,
                          BookService bookService,
                          BookConverter bookSimpleConverter) {
        this.userService = userService;
        this.userFullConverter = userFullConverter;
        this.userSignUpConverter = userSignUpConverter;
        this.bookConverter = bookSimpleConverter;
    }


    @GetMapping
    public ResponseEntity<Set<UserDto>> findAllUsers() {
        Set<User> users = userService.findAll();
        Set<UserDto> userDtos = new HashSet<>();

        for (User user : users){
            UserDto userDto = userFullConverter.convertToDto(user);
            userDtos.add(userDto);
        }

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> findUser(@PathVariable String username) {
        User foundUser = userService.findByUsername(username);
        UserDto foundUserDto = userFullConverter.convertToDto(foundUser);
        return ResponseEntity.ok(foundUserDto);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserSignUpDto userSignUpDto) {
        userService.save(userSignUpConverter.convertToEntity(userSignUpDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/{username}")
    public ResponseEntity<HttpStatus> addBook(@PathVariable String username, @RequestBody BookDto bookSimpleDto) {
        Book wantedBook = bookConverter.convertToEntity(bookSimpleDto);
        userService.addBook(username,wantedBook);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<HttpStatus> removeBook(@PathVariable String username, @RequestBody BookDto bookSimpleDto) {
        Book wantedBook = bookConverter.convertToEntity(bookSimpleDto);
        userService.removeBook(username, wantedBook);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<HttpStatus> deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.ok().build();
    }

}
