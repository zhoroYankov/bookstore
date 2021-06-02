package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.repository.BookRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findByTitle(@NonNull String title) {
        Book foundBook = bookRepository.findByTitle(title).
                orElseThrow(() -> new DataNotFoundException(String.format("Book with title: %s does not exist.", title)));
        return foundBook;
    }

    public Set<Book> findByGenre(String name){
        List<Book> booksList = bookRepository.findAll();
        Set<Book> wantedBooks = new HashSet<>();
        for (Book book : booksList){
            if (name.equals(book.getGenre().toString())){
                wantedBooks.add(book);
            }
        }
        return wantedBooks;
    }

    public Set<Book> findAll() {
        List<Book> booksList = bookRepository.findAll();
        Set<Book> books = new HashSet<>(booksList);

        return books;
    }


    public void save(@NonNull Book book) {
        bookRepository.save(book);
    }

    public void delete(@NonNull String title) {
        bookRepository.deleteByTitle(title);
    }


}
