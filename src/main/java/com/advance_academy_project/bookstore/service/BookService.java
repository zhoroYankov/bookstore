package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.repository.BookRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findByTitle(String title) {
        Book foundBook = bookRepository.findByTitle(title).
                orElseThrow(() -> new DataNotFoundException(String.format("Book with title: %s does not exist.", title)));
        return foundBook;
    }

    public List<Book> findAllByGenre(String genre){
        List<Book> booksList = bookRepository.findAll();
        List<Book> wantedBooks = new ArrayList<>();
        for (Book book : booksList){
            if (genre.equals(book.getGenre().toString())){
                wantedBooks.add(book);
            }
        }
        return wantedBooks;
    }

    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();

        return books;
    }


    public void save(@NonNull Book book) {
        bookRepository.save(book);
    }

    public void delete(@NonNull String title) {
        bookRepository.deleteByTitle(title);
    }


}
