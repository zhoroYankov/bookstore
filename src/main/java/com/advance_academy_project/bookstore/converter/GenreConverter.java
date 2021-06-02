package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.GenreDto;
import com.advance_academy_project.bookstore.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter {

    public Genre convertToEntity(GenreDto genreDto){
        Genre genre = Genre.builder()
                .id(genreDto.getId())
                .name(genreDto.getName())
                .build();
        return genre;
    }

    public GenreDto convertToDto(Genre genre){
        GenreDto genreDto = GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
        return genreDto;
    }
}
