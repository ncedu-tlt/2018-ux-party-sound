package ru.ncedu.partysound.models.dto;

import ru.ncedu.partysound.models.domain.PlaylistTrackId;

public class PlaylistTrackDTO {

    private PlaylistTrackId id;
    private TrackFromBodyDTO track;
    private int trackNumberInPlaylist;

    public PlaylistTrackDTO(PlaylistTrackId id, TrackFromBodyDTO track, int trackNumberInPlaylist) {
        this.id = id;
        this.track = track;
        this.trackNumberInPlaylist = trackNumberInPlaylist;
    }

    public PlaylistTrackDTO() {
    }

    public PlaylistTrackId getId() {
        return id;
    }

    public void setId(PlaylistTrackId id) {
        this.id = id;
    }

    public TrackFromBodyDTO getTrack() {
        return track;
    }

    public void setTrack(TrackFromBodyDTO track) {
        this.track = track;
    }

    public int getTrackNumberInPlaylist() {
        return trackNumberInPlaylist;
    }

    public void setTrackNumberInPlaylist(int trackNumberInPlaylist) {
        this.trackNumberInPlaylist = trackNumberInPlaylist;
    }
}
