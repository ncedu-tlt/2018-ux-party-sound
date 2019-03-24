package ru.ncedu.partysound.services;

import org.springframework.data.domain.PageRequest;
import ru.ncedu.partysound.models.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistService {
    List<PlaylistDTO> findByPageForAnonymous(PageRequest pageRequest);
}
