package ru.ncedu.partysound.controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.converters.TracksMapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.PlaylistsWithTracksDTO;
import ru.ncedu.partysound.models.dto.TracksWithGenresDTO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.repositories.TracksRepository;
import ru.ncedu.partysound.services.PlaylistsService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/tracks")
public class TracksController {

    private final TracksRepository tracksRepository;
    private final GenresRepository genresRepository;
    private final TracksMapper tracksMapper = Mappers.getMapper(TracksMapper.class);

    @Autowired
    private final PlaylistsService playlistsService;

    public TracksController(TracksRepository tracksRepository, GenresRepository genresRepository, PlaylistsService playlistsService) {
        this.tracksRepository = tracksRepository;
        this.genresRepository = genresRepository;
        this.playlistsService = playlistsService;
    }

    @PostMapping
    public String post(@RequestBody TracksWithGenresDTO track) {
        List<String> genres = track.getGenresString();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            genresDAOS.addAll(genresRepository.findAllByName(genre));
        });
        TracksDAO tracksDAO = tracksMapper.toDAO(track);
        tracksDAO.setGenres(genresDAOS);
        tracksRepository.save(tracksDAO);
        return "Трек успешно сохранен с id" + track.getId();
    }

    @GetMapping
    public PlaylistsWithTracksDTO getPlaylistById(@RequestParam(value = "playlistId")long playlistId){
        PlaylistsWithTracksDTO res = playlistsService.getPlaylistById(playlistId);
        return res;
    }
}
