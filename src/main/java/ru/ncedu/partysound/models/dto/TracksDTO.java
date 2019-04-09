package ru.ncedu.partysound.models.dto;

public class TracksDTO {

    private long id;
    private String name;
    private String artistName;
    private String url;
    private int duration;

    public TracksDTO() {
    }

    public TracksDTO(long id, String name, String artistName, String url, int duration) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.url = url;
        this.duration = duration;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
