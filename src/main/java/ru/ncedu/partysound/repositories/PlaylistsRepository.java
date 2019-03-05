package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

import java.util.List;

public interface PlaylistsRepository extends CrudRepository<PlaylistsDAO, Long> {
    List<PlaylistsDAO> findAll();
}
