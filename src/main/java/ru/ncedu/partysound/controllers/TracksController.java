package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.repositories.TracksRepository;


@RestController
@RequestMapping("/api/tracks")
public class TracksController {

    private final TracksRepository tracksRepository;

    public TracksController(TracksRepository tracksRepository) {
        this.tracksRepository = tracksRepository;
    }

    @PostMapping
    public String post(@RequestBody TracksDAO track) {
        tracksRepository.save(track);
        return "Трек успешно сохранен с id" + track.getId();
    }
}
