package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.AuthorDto;
import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.model.Author;
import com.advance_academy_project.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorConverter {

    private final BookConverter bookConverter;

    @Autowired
    public AuthorConverter(BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }


    public Author convertToEntity(AuthorDto authorDto) {
        Set<Book> books = new HashSet<>();
        Set<BookDto> bookDtos = authorDto.getBooks();

        for (BookDto bookDto : bookDtos) {
            Book book = bookConverter.convertToEntity(bookDto);
            books.add(book);
        }

        Author author = Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .books(books)
                .build();
        return author;
    }

    public AuthorDto convertToDto(Author author) {
        Set<BookDto> bookDtos = new HashSet<>();
        Set<Book> books = author.getBooks();

        for (Book book : books) {
            BookDto bookDto = bookConverter.convertToDto(book);
            bookDtos.add(bookDto);
        }

        AuthorDto authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .books(bookDtos)
                .build();

        return authorDto;
    }
}
