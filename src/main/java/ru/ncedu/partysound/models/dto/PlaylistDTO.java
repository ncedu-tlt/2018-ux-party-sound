package ru.ncedu.partysound.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PlaylistDTO {
    private long id;
    private String name;
    private String description;
    private String image;
    private boolean privateAccess;
    private String link;
    private int rating;
    private Set<String> genres;
    private List<PlaylistTrackDTO> tracks = new ArrayList<>();

    public PlaylistDTO() {
    }

    public PlaylistDTO(long id, String name, String description, String image, boolean privateAccess, String link, int rating, Set<String> genres, List<PlaylistTrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.privateAccess = privateAccess;
        this.link = link;
        this.rating = rating;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public List<PlaylistTrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<PlaylistTrackDTO> tracks) {
        this.tracks = tracks;
    }
}
