package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tracks")
public class TracksDAO {

    @Id
    private long id;
    private String url;
    private String name;
    private String artistName;
    private long artistId;
    private String albumName;
    private long albumId;
    private String genre;

    @OneToMany(
            mappedBy = "track",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<PlaylistTrackDAO> playlists = new ArrayList<>();

    public TracksDAO() {
    }

    public TracksDAO(long id, String url, String name, String artistName, long artistId, String albumName, long albumId, String genre) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.artistName = artistName;
        this.artistId = artistId;
        this.albumName = albumName;
        this.albumId = albumId;
        this.genre = genre;
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

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TracksDAO tracksDAO = (TracksDAO) o;
        return artistId == tracksDAO.artistId &&
                albumId == tracksDAO.albumId &&
                Objects.equals(name, tracksDAO.name) &&
                Objects.equals(artistName, tracksDAO.artistName) &&
                Objects.equals(albumName, tracksDAO.albumName) &&
                Objects.equals(genre, tracksDAO.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artistName, artistId, albumName, albumId, genre);
    }
}
