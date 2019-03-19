package ru.ncedu.partysound.controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.partysound.models.converters.PlaylistDTOMapper;
import ru.ncedu.partysound.models.dto.PlaylistDTO;
import ru.ncedu.partysound.repositories.PlaylistsRepository;

import java.util.List;


@RestController
@RequestMapping("/api/play_lists")
public class PlayListsController {

    private PlaylistDTOMapper mapper = Mappers.getMapper(PlaylistDTOMapper.class);

    private final PlaylistsRepository playlistsRepository;

    public PlayListsController(PlaylistsRepository playlistsRepository) {
        this.playlistsRepository = playlistsRepository;
    }

    @GetMapping
    public List<PlaylistDTO> get() {
        return mapper.toPlaylistsDTOs(playlistsRepository.findAll());
    }
}
