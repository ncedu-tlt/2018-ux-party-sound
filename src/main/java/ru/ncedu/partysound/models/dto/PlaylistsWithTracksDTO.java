package ru.ncedu.partysound.models.dto;

import java.util.List;

public class PlaylistsWithTracksDTO {
    private long id;
    private String name;
    private List<GenresDTO> genres;
    private List<TracksDTO> tracks;

    public PlaylistsWithTracksDTO() {
    }

    public PlaylistsWithTracksDTO(long id, String name, List<GenresDTO> genres, List<TracksDTO> tracks) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GenresDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresDTO> genres) {
        this.genres = genres;
    }

    public List<TracksDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksDTO> tracks) {
        this.tracks = tracks;
    }
}
