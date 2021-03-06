package ru.ncedu.partysound.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

import java.math.BigInteger;
import java.util.List;

public interface PlaylistsRepository extends PagingAndSortingRepository<PlaylistsDAO, Long> {
    Page<PlaylistsDAO> findAllByPrivateAccessFalse(Pageable pageable);

    PlaylistsDAO findById(long id);

    @Query(value = "SELECT plrole.playlist_id\n" +
            "FROM (SELECT user_playlist.playlist_id, COUNT(*) AS roleCount\n" +
            "FROM user_playlist INNER JOIN roles  ON user_playlist.role_id=roles.id\n" +
            "INNER JOIN playlists ON user_playlist.playlist_id=playlists.id\n" +
            "WHERE roles.name='listener' AND playlists.private_access=false\n" +
            "GROUP BY user_playlist.playlist_id) plrole\n" +
            "ORDER BY plrole.roleCount desc\n" +
            "limit 5", nativeQuery = true)
    List<BigInteger> getTopPlaylistsId();

    @Query(value = "SELECT DISTINCT privatePlaylists.id, privatePlaylists.name, privatePlaylists.description, privatePlaylists.private_access " +
            "FROM (SELECT * FROM playlists WHERE playlists.name LIKE %?1% AND playlists.private_access = false) privatePlaylists " +
            "LEFT JOIN playlist_genre ON(privatePlaylists.id = playlist_genre.playlist_id) " +
            "LEFT JOIN genres ON(playlist_genre.genre_id = genres.id) " +
            "LEFT JOIN playlist_track ON(privatePlaylists.id = playlist_track.playlist_id) " +
            "LEFT JOIN tracks ON(playlist_track.track_id = tracks.id) " +
            "WHERE genres.name IN ?2 AND tracks.artist_name LIKE %?3% ",
            countQuery = "SELECT count(*)" +
                    "FROM (SELECT * FROM playlists WHERE playlists.name LIKE %?1% AND playlists.private_access = false) privatePlaylists " +
                    "LEFT JOIN playlist_genre ON(privatePlaylists.id = playlist_genre.playlist_id) " +
                    "LEFT JOIN genres ON(playlist_genre.genre_id = genres.id) " +
                    "LEFT JOIN playlist_track ON(privatePlaylists.id = playlist_track.playlist_id) " +
                    "LEFT JOIN tracks ON(playlist_track.track_id = tracks.id) " +
                    "WHERE genres.name IN ?2 AND tracks.artist_name LIKE %?3% ",
            nativeQuery = true)
    Page<PlaylistsDAO> findAllByNameAndAndGenresAndSingers(String name, String[] genresArray, String singer, Pageable pageable);

    @Query(value = "SELECT DISTINCT privatePlaylists.id, privatePlaylists.name, privatePlaylists.description, privatePlaylists.private_access " +
            "FROM (SELECT * FROM playlists WHERE playlists.name LIKE %?1% AND playlists.private_access = false) privatePlaylists " +
            "LEFT JOIN playlist_track ON(privatePlaylists.id = playlist_track.playlist_id) " +
            "LEFT JOIN tracks ON(playlist_track.track_id = tracks.id) " +
            "WHERE tracks.artist_name LIKE  ?2 ",
            countQuery = "SELECT count(*) " +
                    "FROM (SELECT * FROM playlists WHERE playlists.name LIKE %?1% AND playlists.private_access = false) privatePlaylists " +
                    "LEFT JOIN playlist_track ON(privatePlaylists.id = playlist_track.playlist_id) " +
                    "LEFT JOIN tracks ON(playlist_track.track_id = tracks.id) " +
                    "WHERE tracks.artist_name LIKE  ?2 ",
            nativeQuery = true)
    Page<PlaylistsDAO> findAllByNameAndSinger(String name, String singer, Pageable pageable);

    Page<PlaylistsDAO> findAllByNameContainsAndPrivateAccessFalse(String name, Pageable pageable);
}
