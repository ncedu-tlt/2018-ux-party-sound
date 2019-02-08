package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "playlists")
public class PlaylistsDAO {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private String image;
    private boolean privateAccess;
    private String link;
    private int rating;

    @OneToMany(mappedBy = "playlist")
    private Set<RolesDAO> roles;

    @ElementCollection
    @CollectionTable(
            name="playlist_genre",
            joinColumns=@JoinColumn(name = "playlist_id", referencedColumnName = "id")
    )
    private Set<String> genres;

    @OneToMany(
            mappedBy = "playlist",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlaylistTrackDAO> tracks = new ArrayList<>();

    public void addTrack(TracksDAO track) {
        PlaylistTrackDAO playlistTrack = new PlaylistTrackDAO(this, track);
        tracks.add(playlistTrack);
        track.getPlaylists().add(playlistTrack);
    }

    public void removeTrack(TracksDAO track) {
        for (Iterator<PlaylistTrackDAO> iterator = tracks.iterator();
             iterator.hasNext(); ) {
            PlaylistTrackDAO playlistTrack = iterator.next();

            if (playlistTrack.getPlaylist().equals(this) &&
                    playlistTrack.getTrack().equals(track)) {
                iterator.remove();
                playlistTrack.getTrack().getPlaylists().remove(playlistTrack);
                playlistTrack.setPlaylist(null);
                playlistTrack.setTrack(null);
            }
        }
    }

    public PlaylistsDAO() {}

    public PlaylistsDAO(String name, String description, String image, boolean privateAccess, String link, int rating, Set<RolesDAO> roles, Set<String> genres, List<PlaylistTrackDAO> tracks) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.privateAccess = privateAccess;
        this.link = link;
        this.rating = rating;
        this.roles = roles;
        this.genres = genres;
        this.tracks = tracks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<RolesDAO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesDAO> roles) {
        this.roles = roles;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public List<PlaylistTrackDAO> getTracks() {
        return tracks;
    }

    public void setTracks(List<PlaylistTrackDAO> tracks) {
        this.tracks = tracks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistsDAO that = (PlaylistsDAO) o;
        return rating == that.rating &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(link, that.link) &&
                Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, link, rating, genres);
    }
}
