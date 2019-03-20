package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.PlaylistTrackDAO;
import ru.ncedu.partysound.models.domain.PlaylistTrackId;

import java.util.List;

public interface PlaylistTrackRepository extends CrudRepository<PlaylistTrackDAO, PlaylistTrackId> {

    List<PlaylistTrackDAO> findAll();
}
