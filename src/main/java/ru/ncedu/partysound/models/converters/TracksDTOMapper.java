package ru.ncedu.partysound.models.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TracksDTO;

import java.util.List;

@Mapper
public interface TracksDTOMapper {
    TracksDAO toDTO(TracksDTO playlistsDTO);
}
