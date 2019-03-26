package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;

import java.util.List;

@Mapper
public interface PlaylistsMapper {

    PlaylistsDTO toPlaylistDTO(PlaylistsDAO playlistDAO);

    List<PlaylistsDTO> toPlaylistDTOs(List<PlaylistsDAO> products);
}
