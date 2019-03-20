package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.repositories.TracksRepository;

import java.util.List;


@RestController
@RequestMapping("/api/tracks")
public class TracksController {

    private final TracksRepository tracksRepository;
    private final GenresRepository genresRepository;

    public TracksController(TracksRepository tracksRepository, GenresRepository genresRepository) {
        this.tracksRepository = tracksRepository;
        this.genresRepository = genresRepository;
    }

    @PostMapping
    public String post(@RequestBody TracksDAO track) {
        tracksRepository.save(track);
        List<String> genres = track.getGenres();
        List<GenresDAO> genresDAOS = genresRepository.findAllByName();
        return "Трек успешно сохранен с id" + track.getId();
    }
}
