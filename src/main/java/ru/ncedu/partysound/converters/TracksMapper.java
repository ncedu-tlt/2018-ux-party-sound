package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.ncedu.partysound.models.domain.PlaylistTrackDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TracksDTO;
import ru.ncedu.partysound.models.dto.TracksWithGenresDTO;


@Mapper
public interface TracksMapper {
    TracksDAO toDAO(TracksWithGenresDTO tracksWithGenresDTO);

    @Mappings ({@Mapping(target = "name", source = "track.name"),
            @Mapping(target = "artistName", source = "track.artistName"),
            @Mapping(target = "url", source = "track.url"),
            @Mapping(target = "duration", source = "track.duration")})
    TracksDTO toTracksDTO(PlaylistTrackDAO playlistTrackDAO);


}
