package ru.ncedu.partysound.services;

import ru.ncedu.partysound.models.dto.PlaylistForCreateDTO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.models.dto.PlaylistsWithTracksDTO;

import java.util.List;

public interface PlaylistsService {
    List<PlaylistsDTO> getPlaylists(int pageNumber, int pageSize);

    PlaylistsWithTracksDTO getPlaylistById(long playlistId);

    List<PlaylistsDTO> getTopPlaylists();

    List<PlaylistsDTO> getPlaylistsBySearchParams(int pageNumber, int pageSize, String playlistName, String[] genresArray, String singer);

    boolean createPlaylist (PlaylistForCreateDTO playlistForCreateDTO, String login);
}
