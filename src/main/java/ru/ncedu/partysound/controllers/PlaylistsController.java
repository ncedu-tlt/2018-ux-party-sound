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
public class PlaylistsController {
    @Autowired
    private final PlaylistsService playlistsService;

    public PlaylistsController(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @GetMapping(value = "/api/playlists")
    public List<PlaylistsDTO> getPlaylistsList(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        return playlistsService.getPlaylists(pageNumber, pageSize);
    }

    @GetMapping(value = "/api/topPlaylists")
    public List<PlaylistsDTO> getTopPlaylists(){
        return playlistsService.getTopPlaylists();
    }

    @GetMapping("/filter")
    public List<PlaylistsDTO> getPlaylists(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                           @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                           @RequestParam(value = "playlistName", defaultValue = "") String playlistName,
                                           @RequestParam(value = "genresArray", required = false) String[] genresArray,
                                           @RequestParam(value = "singer", required = false) String singer) {
        return playlistsService.getPlaylistsBySearchParams(pageNumber, pageSize, playlistName, genresArray, singer);
    }
}
