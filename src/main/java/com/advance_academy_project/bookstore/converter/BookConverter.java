package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.model.Author;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.model.Genre;
import com.advance_academy_project.bookstore.service.AuthorService;
import com.advance_academy_project.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {


    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookConverter(AuthorService authorService, GenreService genreService) {
        this.authorService = authorService;
        this.genreService = genreService;
    }


    public Book convertToEntity(BookDto bookDto) {
        Author author = authorService.findByName(bookDto.getAuthor());
        Genre genre = genreService.findByName(bookDto.getGenre());

        Book book = Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .genre(genre)
                .author(author)
                .build();
        return book;
    }

    public BookDto convertToDto(Book book) {

        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(book.getGenre().getName())
                .author(book.getAuthor().getName())
                .build();
        return bookDto;
    }

}
