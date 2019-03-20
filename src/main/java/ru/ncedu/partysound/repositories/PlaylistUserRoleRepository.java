package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.PlaylistUserRoleDAO;

import java.util.List;

public interface PlaylistUserRoleRepository extends CrudRepository<PlaylistUserRoleDAO, Long> {

    List<PlaylistUserRoleDAO> findAll();
}
