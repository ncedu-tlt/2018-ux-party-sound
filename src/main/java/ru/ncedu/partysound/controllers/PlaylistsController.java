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

    public PlaylistsController(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @GetMapping
    public List<PlaylistsDTO> getPlaylistsList(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        return playlistsService.getPlaylists(pageNumber, pageSize);
    }

    @GetMapping("/filter")
    public List<PlaylistsDTO> getPlaylists(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                           @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                           @RequestParam(value = "playlistName", defaultValue = "") String playlistName,
                                           @RequestParam(value = "singersArray") List<String> singersArray,
                                           @RequestParam(value = "genresArray") List<String> genresArray) {
        return playlistsService.getPlaylistsBySearchParams(pageNumber, pageSize, playlistName, singersArray, genresArray);
    }
}
