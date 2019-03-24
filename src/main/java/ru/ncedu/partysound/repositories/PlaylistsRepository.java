package ru.ncedu.partysound.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

import java.util.List;

public interface PlaylistsRepository extends CrudRepository<PlaylistsDAO, Long> {
    Page<PlaylistsDAO> findAll(Pageable pageable);
}
