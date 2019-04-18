package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.dto.GenresDTO;

import java.util.List;

@Mapper
public interface GenresMapper {

    List<GenresDTO> toGenresDTOs(List<GenresDAO> genresDAOList);
}
