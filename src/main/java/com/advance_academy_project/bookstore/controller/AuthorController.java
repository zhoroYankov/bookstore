package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.dto.AuthorDto;
import com.advance_academy_project.bookstore.service.AuthorService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<AuthorDto> findAuthor(@PathVariable String name) {
        AuthorDto authorDto = authorService.findByName(name);
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping
    public ResponseEntity<Set<AuthorDto>> findAllAuthors(){
       Set<AuthorDto> authorsDto = authorService.findAllAuthors();
       return ResponseEntity.ok(authorsDto);
    }

    @PostMapping
    public  ResponseEntity<HttpStatus> saveAuthor(@RequestBody AuthorDto authorDto){
        authorService.saveAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable String name){
        authorService.deleteAuthor(name);
        return ResponseEntity.ok().build();
    }
}