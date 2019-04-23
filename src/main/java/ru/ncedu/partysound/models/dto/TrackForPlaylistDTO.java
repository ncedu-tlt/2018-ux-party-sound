package ru.ncedu.partysound.models.dto;

public class TrackForPlaylistDTO {
    private TracksWithGenresDTO track;
    private long playlistId;

    public TrackForPlaylistDTO() {
    }

    public TrackForPlaylistDTO(TracksWithGenresDTO track, long playlistId) {
        this.track = track;
        this.playlistId = playlistId;
    }

    public TracksWithGenresDTO getTrack() {
        return track;
    }

    public void setTrack(TracksWithGenresDTO track) {
        this.track = track;
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }
}
