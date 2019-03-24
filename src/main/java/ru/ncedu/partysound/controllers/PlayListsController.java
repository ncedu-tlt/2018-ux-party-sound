package ru.ncedu.partysound.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.partysound.models.dto.PlaylistDTO;
import ru.ncedu.partysound.services.PlaylistService;

import java.util.List;


@RestController
@RequestMapping("/api/play-lists")
public class PlayListsController {

    private final PlaylistService playlistService;

    public PlayListsController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    @GetMapping
    public List<PlaylistDTO> get(@RequestParam("page") int page) {
        return playlistService.findByPageForAnonymous(new PageRequest(page, 16));
    }
}
