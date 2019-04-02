package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "playlist_genre")
public class PlaylistGenreDAO {

    @EmbeddedId
    private PlaylistGenreId id;

    @ManyToOne
    @MapsId("playlistId")
    private PlaylistsDAO playlist;

    @ManyToOne
    @MapsId("genreId")
    private GenresDAO genre;

    PlaylistGenreDAO() {
    }

    PlaylistGenreDAO(PlaylistsDAO playlist, GenresDAO genre) {
        this.id = new PlaylistGenreId(playlist.getId(), genre.getId());
        this.playlist = playlist;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlaylistGenreDAO that = (PlaylistGenreDAO) obj;
        return Objects.equals(playlist, that.playlist) &&
                Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, genre);
    }


    public PlaylistGenreId getId() {
        return id;
    }

    public void setId(PlaylistGenreId id) {
        this.id = id;
    }

    public PlaylistsDAO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistsDAO playlist) {
        this.playlist = playlist;
    }

    public GenresDAO getGenre() {
        return genre;
    }

    public void setGenre(GenresDAO genre) {
        this.genre = genre;
    }
}
