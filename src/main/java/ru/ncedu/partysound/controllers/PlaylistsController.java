package ru.ncedu.partysound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.domain.PlaylistTrackId;
import ru.ncedu.partysound.models.domain.PlaylistUserRoleDAO;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.models.dto.PlaylistForCreateDTO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.repositories.PlaylistTrackRepository;
import ru.ncedu.partysound.repositories.PlaylistUserRoleRepository;
import ru.ncedu.partysound.repositories.PlaylistsRepository;
import ru.ncedu.partysound.repositories.UsersRepository;
import ru.ncedu.partysound.services.PlaylistsService;

import java.security.Principal;
import java.util.List;

@RestController
public class PlaylistsController {
    @Autowired
    private final PlaylistsService playlistsService;
    private final UsersRepository usersRepository;
    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistsRepository playlistsRepository;
    private final PlaylistUserRoleRepository playlistUserRoleRepository;

    public PlaylistsController(PlaylistsService playlistsService, UsersRepository usersRepository, PlaylistTrackRepository playlistTrackRepository, PlaylistsRepository playlistsRepository, PlaylistUserRoleRepository playlistUserRoleRepository) {
        this.playlistsService = playlistsService;
        this.usersRepository = usersRepository;
        this.playlistTrackRepository = playlistTrackRepository;
        this.playlistsRepository = playlistsRepository;
        this.playlistUserRoleRepository = playlistUserRoleRepository;
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

    @DeleteMapping("/api/track-delete")
    public boolean deleteTrackByPlaylistIdAndTrackId(@RequestParam(value = "playlistId") long playlistId,
                                                     @RequestParam(value = "trackId") long trackId,
                                                     Principal principal) {
        UsersDAO userDAO = usersRepository.findByLogin(principal.getName());
        PlaylistsDAO playlistDAO = playlistsRepository.findById(playlistId);
        PlaylistUserRoleDAO playlistUserRoleDAO = playlistUserRoleRepository.findByPlaylistAndUser(playlistDAO, userDAO);
        if(playlistUserRoleDAO.getRole().isDeleteTrack()) {
            try{
                playlistTrackRepository.deleteById(new PlaylistTrackId(playlistId, trackId));
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
