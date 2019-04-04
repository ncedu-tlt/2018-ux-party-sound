package ru.ncedu.partysound.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

import java.math.BigInteger;
import java.util.List;

public interface PlaylistsRepository extends PagingAndSortingRepository<PlaylistsDAO,Long> {
    Page<PlaylistsDAO> findAll(Pageable pageable);
    PlaylistsDAO findById(long id);

    @Query( value = "SELECT plrole.playlist_id\n" +
            "FROM (SELECT user_playlist.playlist_id, COUNT(*) AS roleCount\n" +
            "FROM user_playlist INNER JOIN roles  ON user_playlist.role_id=roles.id\n" +
            "INNER JOIN playlists ON user_playlist.playlist_id=playlists.id\n" +
            "WHERE roles.name='listener' AND playlists.private_access=false\n" +
            "GROUP BY user_playlist.playlist_id) plrole\n" +
            "ORDER BY plrole.roleCount desc\n" +
            "limit 5", nativeQuery = true)
    List<BigInteger> getTopPlaylistsId();
}
