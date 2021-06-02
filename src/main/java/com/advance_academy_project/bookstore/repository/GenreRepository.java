package com.advance_academy_project.bookstore.repository;

import com.advance_academy_project.bookstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);

    void deleteByName(String name);

}
