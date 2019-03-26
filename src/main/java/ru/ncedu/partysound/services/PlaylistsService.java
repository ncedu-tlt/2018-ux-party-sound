package ru.ncedu.partysound.services;

import ru.ncedu.partysound.models.dto.PlaylistsDTO;

import java.util.List;

public interface PlaylistsService {
    List<PlaylistsDTO> getPlaylists(int pageNumber, int pageSize);
}
