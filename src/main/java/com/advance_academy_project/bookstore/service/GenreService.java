package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Genre;
import com.advance_academy_project.bookstore.repository.GenreRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    private final GenreRepository genreRepository;


    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findByName(String name){
        return genreRepository.findByName(name)
                .orElseThrow(()-> new DataNotFoundException(String.format("Genre with name %s does not exist", name)));
    }

    public Set<Genre> findAll(){
        List<Genre> genreList = genreRepository.findAll();
        Set<Genre> genres = new HashSet<>(genreList);
        return genres;
    }

    public void save(@NonNull Genre genre){
        genreRepository.save(genre);
    }

    public void deleteByName(@NonNull String name){
        genreRepository.deleteByName(name);
    }

}
