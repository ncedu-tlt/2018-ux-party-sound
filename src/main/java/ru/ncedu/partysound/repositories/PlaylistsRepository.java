package ru.ncedu.partysound.repositories;

import ch.qos.logback.core.db.dialect.SQLiteDialect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

import java.lang.annotation.Native;
import java.util.List;

public interface PlaylistsRepository extends PagingAndSortingRepository<PlaylistsDAO, Long> {
    Page<PlaylistsDAO> findAll(Pageable pageable);

    PlaylistsDAO findById(long id);

    Page<PlaylistsDAO> findByName(Page pageable);

    @Query(value = "select * from playlists playlist where playlist.name like %?1", nativeQuery = true)
    Page<PlaylistsDAO> findAllByNameAndAndGenresAndSingers(String name, List<String> singersArray, List<String> genresArray, Pageable pageable);
}
