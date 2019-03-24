package ru.ncedu.partysound.services;

import org.springframework.stereotype.Service;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.repositories.GenresRepository;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public GenresDAO createGenresByName(String name) {
        GenresDAO genresDAO = new GenresDAO();
        genresDAO.setName(name);
        return genresRepository.save(genresDAO);
    }

}
