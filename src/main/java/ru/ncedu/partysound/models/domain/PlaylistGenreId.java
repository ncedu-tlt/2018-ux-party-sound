package ru.ncedu.partysound.models.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlaylistGenreId implements Serializable {

    private long playlistId;
    private long genreId;

    public PlaylistGenreId() {
    }

    public PlaylistGenreId(long playlistId, long genreId) {
        this.playlistId = playlistId;
        this.genreId = genreId;
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, genreId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PlaylistGenreId that = (PlaylistGenreId) obj;

        return Objects.equals(playlistId, that.playlistId) && Objects.equals(genreId, that.genreId);
    }
}
