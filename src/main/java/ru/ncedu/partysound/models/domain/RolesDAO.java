package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class RolesDAO {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private boolean addTrack;
    private boolean deleteTrack;
    private boolean deleteUser;
    private boolean changeTracksOrder;
    private boolean seePlaylist;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<PlaylistUserRoleDAO> playlistUser = new HashSet<>();

    public RolesDAO() {}

    public RolesDAO(String name, boolean addTrack, boolean deleteTrack, boolean deleteUser, boolean changeTracksOrder, boolean seePlaylist) {
        this.name = name;
        this.addTrack = addTrack;
        this.deleteTrack = deleteTrack;
        this.deleteUser = deleteUser;
        this.changeTracksOrder = changeTracksOrder;
        this.seePlaylist = seePlaylist;
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

    public boolean isAddTrack() {
        return addTrack;
    }

    public void setAddTrack(boolean addTrack) {
        this.addTrack = addTrack;
    }

    public boolean isDeleteTrack() {
        return deleteTrack;
    }

    public void setDeleteTrack(boolean deleteTrack) {
        this.deleteTrack = deleteTrack;
    }

    public boolean isDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(boolean deleteUser) {
        this.deleteUser = deleteUser;
    }

    public boolean isChangeTracksOrder() {
        return changeTracksOrder;
    }

    public void setChangeTracksOrder(boolean changeTracksOrder) {
        this.changeTracksOrder = changeTracksOrder;
    }

    public boolean isSeePlaylist() {
        return seePlaylist;
    }

    public void setSeePlaylist(boolean seePlaylist) {
        this.seePlaylist = seePlaylist;
    }
}
