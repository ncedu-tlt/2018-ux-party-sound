package ru.ncedu.partysound.models.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "genres")
public class GenresDAO {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<PlaylistsDAO> playlists = new HashSet<>();

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<TracksDAO> tracks = new HashSet<>();

    public GenresDAO() {
    }

    public GenresDAO(String name) {
        this.name = name;
    }

    public Set<PlaylistsDAO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlaylistsDAO> playlists) {
        this.playlists = playlists;
    }

    public Set<TracksDAO> getTracks() {
        return tracks;
    }

    public void setTracks(Set<TracksDAO> tracks) {
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
}
