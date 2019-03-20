package ru.ncedu.partysound.models.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "playlists")
public class PlaylistsDAO {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private boolean privateAccess;

    @OneToMany(
            mappedBy = "playlist",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<PlaylistTrackDAO> tracks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "playlist_genre",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private Set<GenresDAO> genres = new HashSet<>();

    @OneToMany(
            mappedBy = "playlist",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<PlaylistUserRoleDAO> userRole = new HashSet<>();

    public PlaylistsDAO() {
    }

    public PlaylistsDAO(String name, String description, boolean privateAccess) {
        this.name = name;
        this.description = description;
        this.privateAccess = privateAccess;
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

    public List<PlaylistTrackDAO> getTracks() {
        return tracks;
    }

    public void setTracks(List<PlaylistTrackDAO> tracks) {
        this.tracks = tracks;
    }

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    public Set<GenresDAO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenresDAO> genres) {
        this.genres = genres;
    }

    public Set<PlaylistUserRoleDAO> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<PlaylistUserRoleDAO> userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistsDAO that = (PlaylistsDAO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, genres);
    }
}
