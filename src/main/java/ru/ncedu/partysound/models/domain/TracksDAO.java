package ru.ncedu.partysound.models.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "tracks")
public class TracksDAO {

    @Id
    private long id;

    private String url;
    private String name;
    private String artistName;
    private long artistId;

    @OneToMany(
            mappedBy = "track",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<PlaylistTrackDAO> playlists = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "track_genre",
            joinColumns = {@JoinColumn(name = "track_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private Set<GenresDAO> genres = new HashSet<>();

    public TracksDAO() {
    }

    public TracksDAO(String url, String name, String artistName, long artistId) {
        this.url = url;
        this.name = name;
        this.artistName = artistName;
        this.artistId = artistId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public List<PlaylistTrackDAO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistTrackDAO> playlists) {
        this.playlists = playlists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<GenresDAO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenresDAO> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TracksDAO tracksDAO = (TracksDAO) o;
        return artistId == tracksDAO.artistId &&
                Objects.equals(name, tracksDAO.name) &&
                Objects.equals(artistName, tracksDAO.artistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artistName, artistId);
    }
}
