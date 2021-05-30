package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.dto.AuthorDto;
import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.dto.GanreDto;
import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Author;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.model.Ganre;
import com.advance_academy_project.bookstore.repository.BookRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GanreService ganreService;


    public BookService(BookRepository bookRepository, AuthorService authorService, GanreService ganreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.ganreService = ganreService;
    }

    public BookDto findByTitle(@NonNull String title) {
        Book foundBook = bookRepository.findByTitle(title).
                orElseThrow(() -> new DataNotFoundException(String.format("Book with title: %s does not exist.", title)));

        BookDto foundBookDto = new BookDto();

        foundBookDto.setId(foundBook.getId());
        foundBookDto.setTitle(foundBook.getTitle());
        foundBookDto.setGenre(foundBook.getGenre().getGanreName());
        foundBookDto.setAuthor(foundBook.getAuthor().getName());

        return foundBookDto;
    }

    public Set<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();

        Set<BookDto> booksDtos = new HashSet<>();

        for (Book book : books) {
            BookDto bookDto = new BookDto();

            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setGenre(book.getGenre().getGanreName());
            bookDto.setAuthor(book.getAuthor().getName());

            booksDtos.add(bookDto);
        }

        return booksDtos;
    }

    public void saveBook(@NonNull BookDto bookDto) {
        Book book = new Book();

        AuthorDto authorDto = authorService.findByName(bookDto.getAuthor());
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setBooks(authorDto.getBooks());

        GanreDto ganreDto = ganreService.findByGanreName(bookDto.getGenre());
        Ganre ganre = new Ganre();
        ganre.setId(ganreDto.getId());
        ganre.setGanreName(ganreDto.getGanreName());

        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setGenre(ganre);
        book.setAuthor(author);

        bookRepository.save(book);
    }

    public void deleteBook(@NonNull String title) {
        bookRepository.deleteByTitle(title);
    }


}
