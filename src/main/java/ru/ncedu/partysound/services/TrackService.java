package ru.ncedu.partysound.services;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.converters.TracksDTOMapper;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TrackFromBodyDTO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.repositories.TracksRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrackService {

    private final GenresService genresService;
    private final TracksRepository tracksRepository;
    private final GenresRepository genresRepository;
    private final TracksDTOMapper tracksDTOMapper = Mappers.getMapper(TracksDTOMapper.class);

    public TrackService(GenresService genresService, TracksRepository tracksRepository, GenresRepository genresRepository) {
        this.genresService = genresService;
        this.tracksRepository = tracksRepository;
        this.genresRepository = genresRepository;
    }

    public TracksDAO createTrackDAOByTrackFromBodyDTO (TrackFromBodyDTO trackFromBodyDTO) {
        List<String> genres = trackFromBodyDTO.getGenresString();
        Set<GenresDAO> genresDAOS = new HashSet<>();
        genres.forEach(genre -> {
            GenresDAO genreDAO = genresRepository.findFirstByName(genre);
            if(genreDAO != null){
                genresDAOS.add(genreDAO);
            } else {
                genresDAOS.add(genresService.createGenresByName(genre));
            }
        });
        TracksDAO tracksDAO = tracksDTOMapper.toDAO(trackFromBodyDTO);
        tracksDAO.setGenres(genresDAOS);
        return tracksRepository.save(tracksDAO);
    }
}
