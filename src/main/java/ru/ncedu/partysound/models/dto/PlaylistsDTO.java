package ru.ncedu.partysound.models.dto;

import java.util.List;

public class PlaylistsDTO {
    private long id;
    private String name;
    private List<GenresDTO> genres;

    public PlaylistsDTO() {
    }

    public PlaylistsDTO(long id, String name, List<GenresDTO> genres) {
        this.id = id;
        this.name = name;
        this.genres = genres;
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
}
