package ru.ncedu.partysound.models.dto;


import java.util.List;

public class TracksWithGenresDTO {

    private long id;
    private String url;
    private String name;
    private String artistName;
    private long artistId;
    private int duration;
    private List<String> genresString;

    public TracksWithGenresDTO() {
    }

    public TracksWithGenresDTO(long id, String url, String name, String artistName, long artistId, int duration, List<String> genresString) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.artistName = artistName;
        this.artistId = artistId;
        this.duration = duration;
        this.genresString = genresString;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<String> getGenresString() {
        return genresString;
    }

    public void setGenresString(List<String> genresString) {
        this.genresString = genresString;
    }
}
