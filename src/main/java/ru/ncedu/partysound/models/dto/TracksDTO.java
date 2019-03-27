package ru.ncedu.partysound.models.dto;

public class TracksDTO {

    private String name;
    private String artistName;
    private String url;

    public TracksDTO() {
    }

    public TracksDTO(String name, String artistName, String url) {
        this.name = name;
        this.artistName = artistName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
