package ru.ncedu.partysound.services;

import org.springframework.stereotype.Service;
import ru.ncedu.partysound.models.domain.GenresDAO;
import ru.ncedu.partysound.repositories.GenresRepository;

public interface GenresService {
    GenresDAO createGenre(String name);
}
