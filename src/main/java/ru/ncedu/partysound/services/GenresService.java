package ru.ncedu.partysound.services;

import ru.ncedu.partysound.models.dto.GenresDTO;

import java.util.List;

public interface GenresService {
    List<GenresDTO> getGenresList();
}
