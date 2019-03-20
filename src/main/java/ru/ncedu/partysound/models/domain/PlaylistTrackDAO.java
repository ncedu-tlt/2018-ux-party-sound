package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "playlist_track")
public class PlaylistTrackDAO {

    @EmbeddedId
    private PlaylistTrackId id;

    @ManyToOne
    @MapsId("playlistId")
    private PlaylistsDAO playlist;

    @ManyToOne
    @MapsId("trackId")
    private TracksDAO track;

    private int trackNumberInPlaylist;

    public PlaylistTrackDAO() {}

    public PlaylistTrackDAO(PlaylistsDAO playlist, TracksDAO track) {
        this.playlist = playlist;
        this.track = track;
        this.id = new PlaylistTrackId(playlist.getId(), track.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistTrackDAO that = (PlaylistTrackDAO) o;
        return Objects.equals(playlist, that.playlist) &&
                Objects.equals(track, that.track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, track);
    }

    public PlaylistTrackId getId() {
        return id;
    }

    public void setId(PlaylistTrackId id) {
        this.id = id;
    }

    public PlaylistsDAO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistsDAO playlist) {
        this.playlist = playlist;
    }

    public TracksDAO getTrack() {
        return track;
    }

    public void setTrack(TracksDAO track) {
        this.track = track;
    }

    public int getTrackNumberInPlaylist() {
        return trackNumberInPlaylist;
    }

    public void setTrackNumberInPlaylist(int trackNumberInPlaylist) {
        this.trackNumberInPlaylist = trackNumberInPlaylist;
    }
}
