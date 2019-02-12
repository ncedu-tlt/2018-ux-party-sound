package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.TracksDAO;

public interface TracksRepository extends CrudRepository<TracksDAO,Long> {
}
