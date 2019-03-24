package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.models.dto.PlaylistDTO;

import java.util.List;

@Mapper
public interface PlaylistDTOMapper {
    List<PlaylistDTO> toPlaylistDTOs(List<PlaylistsDAO> playlistDAO);
}
