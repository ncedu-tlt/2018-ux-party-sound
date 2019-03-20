package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.TracksDAO;

import java.util.List;

public interface TracksRepository extends CrudRepository<TracksDAO,Long> {
    List<TracksDAO> findAll();
}
