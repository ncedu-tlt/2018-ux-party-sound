package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.PlaylistUserRoleDAO;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.models.dto.PlaylistForCreateDTO;

import java.util.List;

public interface PlaylistUserRoleRepository extends CrudRepository<PlaylistUserRoleDAO, Long> {

    PlaylistUserRoleDAO findByPlaylistAndUser(PlaylistsDAO playlistsDAO, UsersDAO usersDAO);

    List<PlaylistUserRoleDAO> findAll();
}
