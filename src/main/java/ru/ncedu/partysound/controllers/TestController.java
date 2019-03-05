package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.domain.TrackDTO;
import ru.ncedu.partysound.models.domain.TracksDAO;


@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String get() {
        return "Hello world";
    }

    @PostMapping
    public TracksDAO post(@RequestBody TrackDTO track) {
        return new TracksDAO(
                track.getAudio(),
                track.getName(),
                track.getArtist_name(),
                track.getArtist_id(),
                track.getAlbum_name(),
                track.getAlbum_id(),
                track.getGenre()
        );
    }
}
