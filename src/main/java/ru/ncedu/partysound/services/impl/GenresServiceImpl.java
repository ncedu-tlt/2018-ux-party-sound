package ru.ncedu.partysound.services.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.converters.GenresMapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.dto.GenresDTO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.services.GenresService;

import java.util.List;

@Service
public class GenresServiceImpl implements GenresService {

    @Autowired
    private final GenresRepository genresRepository;
    private final GenresMapper genresMapper = Mappers.getMapper(GenresMapper.class);

    public GenresServiceImpl(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Override
    public List<GenresDTO> getGenresList() {

        List<GenresDAO> genresDAOList = genresRepository.findAll();
        return genresMapper.toGenresDTOs(genresDAOList);
    }
}
