package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<Set<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> findUser(@PathVariable String username){
        UserDto foundUser = userService.findByUsername(username);
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping(value = "/{username}")
    public  ResponseEntity<HttpStatus> deleteByUsername(@PathVariable String username){
        userService.deleteByUsername(username);
        return ResponseEntity.ok().build();
    }

}
