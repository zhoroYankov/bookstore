package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(value = "/{title}")
    public ResponseEntity<BookDto> findByTitle(@PathVariable String title) {
        BookDto foundBookDto = bookService.findByTitle(title);
        return ResponseEntity.ok(foundBookDto);
    }

    @GetMapping
    public ResponseEntity<Set<BookDto>> findAllBooks() {
        Set<BookDto> allBooks = bookService.findAll();
        return ResponseEntity.ok(allBooks);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteBook(String title){
        bookService.deleteBook(title);
        return ResponseEntity.ok().build();
    }

}
