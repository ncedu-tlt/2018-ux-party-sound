package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.repositories.PlaylistsRepository;

import java.util.List;


@RestController
@RequestMapping("/api/play_lists")
public class PlayListsController {

    private final PlaylistsRepository playlistsRepository;

    public PlayListsController(PlaylistsRepository playlistsRepository) {
        this.playlistsRepository = playlistsRepository;
    }

    @GetMapping
    public List<PlaylistsDAO> get() {
        return playlistsRepository.findAll();
    }
}
