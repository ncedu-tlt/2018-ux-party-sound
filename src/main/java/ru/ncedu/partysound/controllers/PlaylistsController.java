package ru.ncedu.partysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.dto.PlaylistForCreateDTO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.services.PlaylistsService;

import java.security.Principal;
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
    public List<PlaylistsDTO> getTopPlaylists() {
        return playlistsService.getTopPlaylists();
    }

    @GetMapping("/api/filter")
    public List<PlaylistsDTO> getPlaylists(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                           @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                           @RequestParam(value = "playlistName", defaultValue = "") String playlistName,
                                           @RequestParam(value = "genresArray", defaultValue = "empty") String[] genresArray,
                                           @RequestParam(value = "singer", defaultValue = "") String singer) {
        return playlistsService.getPlaylistsBySearchParams(pageNumber, pageSize, playlistName, genresArray, singer);
    }

    @PostMapping("/api/create-playlist")
    public boolean createPlaylist(@RequestBody PlaylistForCreateDTO playlistForCreateDTO, Principal principal) {
        System.out.println(playlistForCreateDTO.getName());
        playlistsService.createPlaylist(playlistForCreateDTO, principal.getName());
        return true;
    }
}
