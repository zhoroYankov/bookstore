package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.dto.GanreDto;
import com.advance_academy_project.bookstore.model.Ganre;
import com.advance_academy_project.bookstore.repository.GanreRepository;
import org.springframework.stereotype.Service;

@Service
public class GanreService {

    private final GanreRepository ganreRepository;

    public GanreService(GanreRepository ganreRepository) {
        this.ganreRepository = ganreRepository;
    }

    public GanreDto findByGanreName(String ganreName){
        Ganre foundGanre = ganreRepository.findByGanreName(ganreName)
                .orElseThrow();

        GanreDto ganreDto = new GanreDto();

        ganreDto.setId(foundGanre.getId());
        ganreDto.setGanreName(foundGanre.getGanreName());

        return ganreDto;
    }
}
