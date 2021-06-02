package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Author;
import com.advance_academy_project.bookstore.repository.AuthorRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findByName(@NonNull String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(String.format("Author with name: %s does not exist.", name)));
    }

    public Set<Author> findAll() {
        List<Author> authorsList = authorRepository.findAll();
        Set<Author> authors = new HashSet<>(authorsList);

        return authors;
    }


    public void save(@NonNull Author author) {
        Author wantedAuthor = authorRepository.findByName(author.getName())
                .orElseThrow(() -> new DataNotFoundException(String.format("Author with name %s does not exis.", author.getName())));

        authorRepository.save(wantedAuthor);
    }


    public void delete(@NonNull String name) {
        authorRepository.deleteByName(name);
    }
}
