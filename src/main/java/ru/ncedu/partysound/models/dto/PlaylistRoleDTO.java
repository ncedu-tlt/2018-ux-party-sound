package ru.ncedu.partysound.models.dto;


public class PlaylistRoleDTO {
    private PlaylistsWithTracksDTO playlistsWithTracksDTO;
    private RolesDTO rolesDTO;

    public PlaylistRoleDTO() {
    }

    public PlaylistRoleDTO(PlaylistsWithTracksDTO playlistsWithTracksDTO, RolesDTO rolesDTO) {
        this.playlistsWithTracksDTO = playlistsWithTracksDTO;
        this.rolesDTO = rolesDTO;
    }

    public PlaylistsWithTracksDTO getPlaylistsWithTracksDTO() {
        return playlistsWithTracksDTO;
    }

    public void setPlaylistsWithTracksDTO(PlaylistsWithTracksDTO playlistsWithTracksDTO) {
        this.playlistsWithTracksDTO = playlistsWithTracksDTO;
    }

    public RolesDTO getRolesDTO() {
        return rolesDTO;
    }

    public void setRolesDTO(RolesDTO rolesDTO) {
        this.rolesDTO = rolesDTO;
    }
}
