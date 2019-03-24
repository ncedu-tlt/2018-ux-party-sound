package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TrackDTO;


@Mapper
public interface TracksDTOMapper {
    TracksDAO toDAO(TrackDTO trackDTO);
}
