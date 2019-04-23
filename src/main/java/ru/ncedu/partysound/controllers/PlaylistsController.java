package ru.ncedu.partysound.controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.converters.TracksMapper;
import ru.ncedu.partysound.models.domain.*;
import ru.ncedu.partysound.models.dto.PlaylistForCreateDTO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.models.dto.TrackForPlaylistDTO;
import ru.ncedu.partysound.repositories.*;
import ru.ncedu.partysound.services.PlaylistsService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PlaylistsController {
    @Autowired
    private final PlaylistsService playlistsService;
    private final UsersRepository usersRepository;
    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistsRepository playlistsRepository;
    private final PlaylistUserRoleRepository playlistUserRoleRepository;
    private final GenresRepository genresRepository;
    private final TracksRepository tracksRepository;
    private final TracksMapper tracksMapper = Mappers.getMapper(TracksMapper.class);

    public PlaylistsController(PlaylistsService playlistsService, UsersRepository usersRepository, PlaylistTrackRepository playlistTrackRepository, PlaylistsRepository playlistsRepository, PlaylistUserRoleRepository playlistUserRoleRepository, GenresRepository genresRepository, TracksRepository tracksRepository) {
        this.playlistsService = playlistsService;
        this.usersRepository = usersRepository;
        this.playlistTrackRepository = playlistTrackRepository;
        this.playlistsRepository = playlistsRepository;
        this.playlistUserRoleRepository = playlistUserRoleRepository;
        this.genresRepository = genresRepository;
        this.tracksRepository = tracksRepository;
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

    @PostMapping("/api/tract/add-on-playlist")
    public boolean addTrackOnPlaylist(@RequestBody TrackForPlaylistDTO trackForPlaylistDTO, Principal principal) {
        List<String> genres = trackForPlaylistDTO.getTrack().getGenresString();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            genresDAOS.addAll(genresRepository.findAllByName(genre));
        });
        TracksDAO tracksDAO = tracksMapper.toDAO(trackForPlaylistDTO.getTrack());
        tracksDAO.setGenres(genresDAOS);
        TracksDAO newTrackDAO = tracksRepository.save(tracksDAO);
        PlaylistTrackDAO playlistTrackDAO = new PlaylistTrackDAO();
        playlistTrackDAO.setId(new PlaylistTrackId(trackForPlaylistDTO.getPlaylistId(), newTrackDAO.getId()));
        playlistTrackDAO.setPlaylist(playlistsRepository.findById(trackForPlaylistDTO.getPlaylistId()));
        playlistTrackDAO.setTrack(newTrackDAO);
        playlistTrackDAO.setTrackNumberInPlaylist(1);
        playlistTrackRepository.save(playlistTrackDAO);
        return true;
    }
}
