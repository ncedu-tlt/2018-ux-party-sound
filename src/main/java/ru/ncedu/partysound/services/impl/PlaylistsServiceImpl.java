package ru.ncedu.partysound.services.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.converters.PlaylistsMapper;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.repositories.PlaylistsRepository;
import ru.ncedu.partysound.services.PlaylistsService;

import java.util.List;

@Service
public class PlaylistsServiceImpl implements PlaylistsService {

    @Autowired
    private final PlaylistsRepository playlistsRepository;
    private final PlaylistsMapper playlistsMapper = Mappers.getMapper(PlaylistsMapper.class);

    public PlaylistsServiceImpl(PlaylistsRepository playlistsRepository) {
        this.playlistsRepository = playlistsRepository;
    }

    @Override
    public List<PlaylistsDTO> getPlaylists(int pageNumber, int pageSize) {
        Pageable playlistPage = PageRequest.of(pageNumber, pageSize);
        Page<PlaylistsDAO> playlistsDAOPage = playlistsRepository.findAll(playlistPage);
        return playlistsMapper.toPlaylistDTOs(playlistsDAOPage.getContent());
    }
}
