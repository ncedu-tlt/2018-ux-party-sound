package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.dto.TrackFromBodyDTO;
import ru.ncedu.partysound.services.TrackService;


@RestController
@RequestMapping("/api/tracks")
public class TracksController {

    private final TrackService trackService;

    public TracksController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public String post(@RequestBody TrackFromBodyDTO track) {
        return "Трек с id: " + trackService.createTrackDAOByTrackFromBodyDTO(track).getId() + " создан";
    }
}
