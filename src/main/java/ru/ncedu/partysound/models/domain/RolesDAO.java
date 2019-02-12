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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", referencedColumnName = "id", nullable = false)
    private PlaylistsDAO playlist;

    private String roleName;
    private boolean canAddTrack;
    private boolean canDeleteTrack;
    private boolean canDeleteUser;
    private boolean canChangeTracksOrder;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UsersDAO> users = new HashSet<>();

    public RolesDAO() {}

    public RolesDAO(PlaylistsDAO playlist, String roleName, boolean canAddTrack, boolean canDeleteTrack, boolean canDeleteUser, boolean canChangeTracksOrder) {
        this.playlist = playlist;
        this.roleName = roleName;
        this.canAddTrack = canAddTrack;
        this.canDeleteTrack = canDeleteTrack;
        this.canDeleteUser = canDeleteUser;
        this.canChangeTracksOrder = canChangeTracksOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlaylistsDAO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistsDAO playlist) {
        this.playlist = playlist;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isCanAddTrack() {
        return canAddTrack;
    }

    public void setCanAddTrack(boolean canAddTrack) {
        this.canAddTrack = canAddTrack;
    }

    public boolean isCanDeleteTrack() {
        return canDeleteTrack;
    }

    public void setCanDeleteTrack(boolean canDeleteTrack) {
        this.canDeleteTrack = canDeleteTrack;
    }

    public boolean isCanDeleteUser() {
        return canDeleteUser;
    }

    public void setCanDeleteUser(boolean canDeleteUser) {
        this.canDeleteUser = canDeleteUser;
    }

    public boolean isCanChangeTracksOrder() {
        return canChangeTracksOrder;
    }

    public void setCanChangeTracksOrder(boolean canChangeTracksOrder) {
        this.canChangeTracksOrder = canChangeTracksOrder;
    }

    public Set<UsersDAO> getUsers() {
        return users;
    }

    public void setUsers(Set<UsersDAO> users) {
        this.users = users;
    }
}
