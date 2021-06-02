package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.converter.AuthorConverter;
import com.advance_academy_project.bookstore.dto.AuthorDto;
import com.advance_academy_project.bookstore.model.Author;
import com.advance_academy_project.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorConverter authorConverter;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorConverter authorConverter) {
        this.authorService = authorService;
        this.authorConverter = authorConverter;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<AuthorDto> findAuthor(@PathVariable String name) {
        Author author = authorService.findByName(name);
        AuthorDto authorDto = authorConverter.convertToDto(author);
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping
    public ResponseEntity<Set<AuthorDto>> findAllAuthors(){
       Set<Author> authors = authorService.findAll();
       Set<AuthorDto> authorDtos = new HashSet<>();

       for (Author author : authors){
           AuthorDto authorDto = authorConverter.convertToDto(author);
           authorDtos.add(authorDto);
       }
       return ResponseEntity.ok(authorDtos);
    }

    @PostMapping
    public  ResponseEntity<HttpStatus> saveAuthor(@RequestBody AuthorDto authorDto){
        authorService.save(authorConverter.convertToEntity(authorDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable String name){
        authorService.delete(name);
        return ResponseEntity.ok().build();
    }
}