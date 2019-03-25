package ru.ncedu.partysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.services.PlaylistsService;

import java.util.List;

@RestController
@RequestMapping("api/playlists")
public class PlaylistsController {
    @Autowired
    private final PlaylistsService playlistsService;

    private static final int PAGE_SIZE = 15;

    public PlaylistsController(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @GetMapping
    public List<PlaylistsDTO> getPlaylistsList(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {
        return playlistsService.getPlaylists(pageNumber, PAGE_SIZE);
    }
}
