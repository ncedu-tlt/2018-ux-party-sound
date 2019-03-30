package ru.ncedu.partysound.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ncedu.partysound.models.domain.PlaylistsDAO;

public interface PlaylistsRepository extends PagingAndSortingRepository<PlaylistsDAO,Long> {
    Page<PlaylistsDAO> findAllByPrivateAccessFalse(Pageable pageable);
    Page<PlaylistsDAO> findAll(Pageable pageable);
    PlaylistsDAO findById(long id);
}
