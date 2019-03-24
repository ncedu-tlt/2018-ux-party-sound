package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TrackFromBodyDTO;


@Mapper
public interface TracksDTOMapper {
    TracksDAO toDAO(TrackFromBodyDTO trackFromBodyDTO);
}
