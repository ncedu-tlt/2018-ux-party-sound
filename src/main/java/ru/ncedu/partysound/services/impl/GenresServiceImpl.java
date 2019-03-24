package ru.ncedu.partysound.services.impl;

import org.springframework.stereotype.Service;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.repositories.GenresRepository;
import ru.ncedu.partysound.services.GenresService;

@Service
public class GenresServiceImpl implements GenresService {

    private final GenresRepository genresRepository;

    public GenresServiceImpl(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Override
    public GenresDAO createGenre(String name) {
        GenresDAO genresDAO = new GenresDAO();
        genresDAO.setName(name);
        return genresRepository.save(genresDAO);
    }
}
