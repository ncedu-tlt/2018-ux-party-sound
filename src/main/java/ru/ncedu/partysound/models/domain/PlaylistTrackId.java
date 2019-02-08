package ru.ncedu.partysound.models.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlaylistTrackId implements Serializable {

    @Column(name = "playlist_id")
    private long playlistId;

    @Column(name = "track_id")
    private long trackId;

    public PlaylistTrackId() {}

    public PlaylistTrackId(long playlistId, long trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, trackId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PlaylistTrackId that = (PlaylistTrackId) o;
        return Objects.equals(playlistId, that.playlistId) &&
                Objects.equals(trackId, that.trackId);
    }
}
