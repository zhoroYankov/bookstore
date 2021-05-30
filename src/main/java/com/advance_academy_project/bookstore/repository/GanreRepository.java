package com.advance_academy_project.bookstore.repository;

import com.advance_academy_project.bookstore.model.Ganre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GanreRepository extends JpaRepository<Ganre, Long> {

    Optional<Ganre> findByGanreName(String ganreName);
}
