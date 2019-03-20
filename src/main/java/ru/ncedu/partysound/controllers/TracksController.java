package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.converters.MyMapper;
import ru.ncedu.partysound.models.converters.TracksDTOMapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TracksDTO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.repositories.TracksRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/tracks")
public class TracksController {

    private final TracksRepository tracksRepository;
    private final GenresRepository genresRepository;
    private final TracksDTOMapper tracksDTOMapper;
    private final MyMapper myMapper;

    public TracksController(TracksRepository tracksRepository, GenresRepository genresRepository,
                            TracksDTOMapper tracksDTOMapper, MyMapper myMapper) {
        this.tracksRepository = tracksRepository;
        this.genresRepository = genresRepository;
        this.tracksDTOMapper = tracksDTOMapper;
        this.myMapper = myMapper;
    }

    @PostMapping
    public String post(@RequestBody TracksDTO track) {
        TracksDAO tracksDAO = tracksDTOMapper.toDTO(track);
        Set<String> genres = track.getGenres();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            genresDAOS.addAll(genresRepository.findAllByName(genre));
        });
        tracksDAO.setGenres(genresDAOS);
        tracksRepository.save(tracksDAO);
        return "Трек успешно сохранен с id" + track.getId();
    }
}
