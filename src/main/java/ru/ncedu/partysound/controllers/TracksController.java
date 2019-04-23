package ru.ncedu.partysound.controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.converters.RolesMapper;
import ru.ncedu.partysound.converters.TracksMapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.domain.PlaylistUserRoleDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.PlaylistRoleDTO;
import ru.ncedu.partysound.models.dto.PlaylistsWithTracksDTO;
import ru.ncedu.partysound.models.dto.RolesDTO;
import ru.ncedu.partysound.models.dto.TracksWithGenresDTO;
import ru.ncedu.partysound.repositories.*;
import ru.ncedu.partysound.services.PlaylistsService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/tracks")
public class TracksController {

    private final TracksRepository tracksRepository;
    private final PlaylistsRepository playlistsRepository;
    private final UsersRepository usersRepository;
    private final GenresRepository genresRepository;
    private final PlaylistUserRoleRepository playlistUserRoleRepository;
    private final TracksMapper tracksMapper = Mappers.getMapper(TracksMapper.class);
    private final RolesMapper rolesMapper = Mappers.getMapper(RolesMapper.class);

    @Autowired
    private final PlaylistsService playlistsService;

    public TracksController(TracksRepository tracksRepository, PlaylistsRepository playlistsRepository, UsersRepository usersRepository, GenresRepository genresRepository, PlaylistUserRoleRepository playlistUserRoleRepository, PlaylistsService playlistsService) {
        this.tracksRepository = tracksRepository;
        this.playlistsRepository = playlistsRepository;
        this.usersRepository = usersRepository;
        this.genresRepository = genresRepository;
        this.playlistUserRoleRepository = playlistUserRoleRepository;
        this.playlistsService = playlistsService;
    }

    @PostMapping
    public String post(@RequestBody TracksWithGenresDTO track) {
        List<String> genres = track.getGenresString();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            genresDAOS.addAll(genresRepository.findAllByName(genre));
        });
        TracksDAO tracksDAO = tracksMapper.toDAO(track);
        tracksDAO.setGenres(genresDAOS);
        tracksRepository.save(tracksDAO);
        return "Трек успешно сохранен с id" + track.getId();
    }

    @GetMapping
    public PlaylistsWithTracksDTO getPlaylistById(@RequestParam(value = "playlistId")long playlistId){
        PlaylistsWithTracksDTO res = playlistsService.getPlaylistById(playlistId);
        return res;
    }

    @GetMapping("/with-right")
    public PlaylistRoleDTO getPlaylistByIdWithRight(@RequestParam(value = "playlistId")long playlistId, Principal principal){
        PlaylistRoleDTO playlistRoleDTO = new PlaylistRoleDTO();
        try{
            PlaylistUserRoleDAO playlistUserRoleDAO = playlistUserRoleRepository.findByPlaylistAndUser(playlistsRepository.findById(playlistId),
                    usersRepository.findByLogin(principal.getName()));
            RolesDTO rolesDTO = rolesMapper.toDTO(playlistUserRoleDAO.getRole());
            playlistRoleDTO.setRolesDTO(rolesDTO);
        } catch (NullPointerException e){
            RolesDTO rolesDTO = new RolesDTO();
            rolesDTO.setName("ghost");
            rolesDTO.setAddTrack(false);
            rolesDTO.setChangeTracksOrder(false);
            rolesDTO.setDeleteTrack(false);
            rolesDTO.setDeleteUser(false);
            rolesDTO.setSeePlaylist(true);
            playlistRoleDTO.setRolesDTO(rolesDTO);
        }
        PlaylistsWithTracksDTO playlistsWithTracksDTO = playlistsService.getPlaylistById(playlistId);
        playlistRoleDTO.setPlaylistsWithTracksDTO(playlistsWithTracksDTO);
        return playlistRoleDTO;

    }
}
