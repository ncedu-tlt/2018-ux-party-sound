package ru.ncedu.partysound.models.dto;

import ru.ncedu.partysound.models.domain.PlaylistTrackId;

public class PlaylistTrackDTO {
    private PlaylistTrackId id;
    private TracksDTO track;
    private int orderNumberInPlaylist;
    private int dislikesCount;

    public PlaylistTrackDTO() {
    }

    public PlaylistTrackDTO(PlaylistTrackId id, TracksDTO track, int orderNumberInPlaylist, int dislikesCount) {
        this.id = id;
        this.track = track;
        this.orderNumberInPlaylist = orderNumberInPlaylist;
        this.dislikesCount = dislikesCount;
    }

    public PlaylistTrackId getId() {
        return id;
    }

    public void setId(PlaylistTrackId id) {
        this.id = id;
    }

    public TracksDTO getTrack() {
        return track;
    }

    public void setTrack(TracksDTO track) {
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
