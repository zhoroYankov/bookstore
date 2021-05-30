package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.dto.AuthorDto;
import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Author;
import com.advance_academy_project.bookstore.repository.AuthorRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto findByName(@NonNull String name) {
        Author foundAuthor = authorRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(String.format("Author with name: %s does not exist.", name)));

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(foundAuthor.getId());
        authorDto.setName(foundAuthor.getName());
        authorDto.setBooks(foundAuthor.getBooks());

        return authorDto;
    }

    public Set<AuthorDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        Set<AuthorDto> authorDtos = new HashSet<>();
        for (Author author : authors) {
            AuthorDto authorDto = new AuthorDto();

            authorDto.setId(author.getId());
            authorDto.setName(author.getName());
            authorDto.setBooks(author.getBooks());

            authorDtos.add(authorDto);
        }
        return authorDtos;
    }


    public void saveAuthor(@NonNull AuthorDto authorDto) {
        Author author = new Author();

        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setBooks(authorDto.getBooks());

        authorRepository.save(author);
    }


    public void deleteAuthor(@NonNull String name) {
        authorRepository.deleteByName(name);
    }
}
