package ru.ncedu.partysound.controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.converters.TracksDTOMapper;
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
    private final TracksDTOMapper tracksDTOMapper = Mappers.getMapper(TracksDTOMapper.class);

    public TracksController(TracksRepository tracksRepository, GenresRepository genresRepository) {
        this.tracksRepository = tracksRepository;
        this.genresRepository = genresRepository;
    }

    @PostMapping
    public String post(@RequestBody TracksDTO track) {
        List<String> genres = track.getGenresString();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            genresDAOS.addAll(genresRepository.findAllByName(genre));
        });
        TracksDAO tracksDAO = tracksDTOMapper.toDAO(track);
        tracksDAO.setGenres(genresDAOS);
        tracksRepository.save(tracksDAO);
        return "Трек успешно сохранен с id" + track.getId();
    }
}
