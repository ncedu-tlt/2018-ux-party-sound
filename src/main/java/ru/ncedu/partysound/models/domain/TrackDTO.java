package ru.ncedu.partysound.models.domain;

public class TrackDTO {
    private String name;
    private long artist_id;
    private String artist_name;
    private String album_name;
    private long album_id;
    private String genre;
    private String audio;

    public TrackDTO() {
    }

    public TrackDTO(String name, long artist_id, String artist_name, String album_name, long album_id, String genre, String audio) {
        this.name = name;
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.album_name = album_name;
        this.album_id = album_id;
        this.genre = genre;
        this.audio = audio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist_id(long artist_id) {
        this.artist_id = artist_id;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getName() {
        return name;
    }

    public long getArtist_id() {
        return artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public String getGenre() {
        return genre;
    }

    public String getAudio() {
        return audio;
    }
}
