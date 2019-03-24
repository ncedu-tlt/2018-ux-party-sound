package ru.ncedu.partysound.services.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.converters.PlaylistDTOMapper;
import ru.ncedu.partysound.models.dto.PlaylistDTO;
import ru.ncedu.partysound.repositories.PlaylistsRepository;
import ru.ncedu.partysound.services.PlaylistService;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistDTOMapper mapper = Mappers.getMapper(PlaylistDTOMapper.class);

    private final PlaylistsRepository playlistsRepository;

    public PlaylistServiceImpl(PlaylistsRepository playlistsRepository) {
        this.playlistsRepository = playlistsRepository;
    }

    @Override
    public List<PlaylistDTO> findByPageForAnonymous(PageRequest pageRequest) {
        return mapper.toPlaylistDTOs(playlistsRepository.findAllByPrivateAccessFalse(pageRequest).getContent());
    }
}
