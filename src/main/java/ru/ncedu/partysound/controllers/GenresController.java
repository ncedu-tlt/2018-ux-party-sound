package ru.ncedu.partysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.partysound.models.dto.GenresDTO;
import ru.ncedu.partysound.services.GenresService;

import java.util.List;

@RestController
public class GenresController {

    @Autowired
    private final GenresService genresService;

    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping(value = "/api/genres")
    public List<GenresDTO> getGenresList() {
        return genresService.getGenresList();
    }
}
