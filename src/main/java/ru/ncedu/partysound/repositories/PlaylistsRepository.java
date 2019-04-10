package ru.ncedu.partysound.repositories;

import ch.qos.logback.core.db.dialect.SQLiteDialect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

import java.math.BigInteger;
import java.util.List;

public interface PlaylistsRepository extends PagingAndSortingRepository<PlaylistsDAO,Long> {
import java.lang.annotation.Native;

public interface PlaylistsRepository extends PagingAndSortingRepository<PlaylistsDAO, Long> {
    Page<PlaylistsDAO> findAllByPrivateAccessFalse(Pageable pageable);

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

    String QUERY_REQUEST = "select * from playlists " +
            "left join playlist_genre on(playlists.id = playlist_genre.playlist_id) " +
            "left join genres on(playlist_genre.genre_id = genres.id) " +
            "left join playlist_track on(playlists.id = playlist_track.playlist_id) " +
            "left join tracks on(playlist_track.track_id = tracks.id) " +
            "left join user_playlist on(playlists.id = user_playlist.playlist_id)" +
            "where playlists.name like %?1% and genres.name in ?2 and tracks.artist_name like %?3%";

    @Query(value = QUERY_REQUEST,
            nativeQuery = true)
    Page<PlaylistsDAO> findAllByNameAndAndGenresAndSingers(String name, String[] genresArray, String singer, Pageable pageable);
}
