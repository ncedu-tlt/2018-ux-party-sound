package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "playlist_track")
public class PlaylistTrackDAO {

    @EmbeddedId
    private PlaylistTrackId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private PlaylistsDAO playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private TracksDAO track;

    @Column(name = "order_number_in_playlist")
    private int orderNumberInPlaylist;

    @Column(name = "dislikes_count")
    private int dislikesCount;

    public PlaylistTrackDAO() {}

    public PlaylistTrackDAO(PlaylistsDAO playlist, TracksDAO tracks) {
        this.playlist = playlist;
        this.track = tracks;
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

    public int getOrderNumberInPlaylist() {
        return orderNumberInPlaylist;
    }

    public void setOrderNumberInPlaylist(int orderNumberInPlaylist) {
        this.orderNumberInPlaylist = orderNumberInPlaylist;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }
}
