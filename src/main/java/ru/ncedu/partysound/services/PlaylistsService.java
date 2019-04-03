package ru.ncedu.partysound.services;

import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.models.dto.PlaylistsWithTracksDTO;

import java.util.List;

public interface PlaylistsService {
    List<PlaylistsDTO> getPlaylists(int pageNumber, int pageSize);

    PlaylistsWithTracksDTO getPlaylistById(long playlistId);

    List<PlaylistsDTO> getPlaylistsBySearchParams(int pageNumber, int pageSize, String playlistName, List<String> singersArray, List<String> genresArray);
}
