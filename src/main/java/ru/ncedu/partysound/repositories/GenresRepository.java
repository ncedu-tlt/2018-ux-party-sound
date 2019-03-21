package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.GenresDAO;

import java.util.List;

public interface GenresRepository extends CrudRepository<GenresDAO, Long> {
    List<GenresDAO> findAll();
    List<GenresDAO> findAllByName(String name);
}
