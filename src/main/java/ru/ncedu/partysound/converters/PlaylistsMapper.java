package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.models.dto.PlaylistsWithTracksDTO;

import java.util.List;

@Mapper
public interface PlaylistsMapper {

    PlaylistsDTO toPlaylistDTO(PlaylistsDAO playlistDAO);

    @Mapping(target = "tracks", source = "tracks", ignore = true)
    PlaylistsWithTracksDTO toPlaylistWithTracksDTO(PlaylistsDAO playlistsDAO);

    List<PlaylistsDTO> toPlaylistDTOs(List<PlaylistsDAO> products);
}
