package ru.ncedu.partysound.services.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.converters.TracksDTOMapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TrackDTO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.repositories.TracksRepository;
import ru.ncedu.partysound.services.GenresService;
import ru.ncedu.partysound.services.TrackService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrackServiceImpl implements TrackService {

    private final GenresService genresService;
    private final TracksRepository tracksRepository;
    private final GenresRepository genresRepository;
    private final TracksDTOMapper tracksDTOMapper = Mappers.getMapper(TracksDTOMapper.class);

    public TrackServiceImpl(GenresService genresService, TracksRepository tracksRepository, GenresRepository genresRepository) {
        this.genresService = genresService;
        this.tracksRepository = tracksRepository;
        this.genresRepository = genresRepository;
    }

    @Override
    public TracksDAO createTrack (TrackDTO trackDTO) {
        List<String> genres = trackDTO.getGenresString();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            GenresDAO genreDAO = genresRepository.findFirstByName(genre);
            if(genreDAO != null){
                genresDAOS.add(genreDAO);
            } else {
                genresDAOS.add(genresService.createGenre(genre));
            }
        });
        TracksDAO tracksDAO = tracksDTOMapper.toDAO(trackDTO);
        tracksDAO.setGenres(genresDAOS);
        return tracksRepository.save(tracksDAO);
    }
}
