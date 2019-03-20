package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_playlist")
public class PlaylistUserRoleDAO {

    @EmbeddedId
    private PlaylistUserRoleId id;

    @ManyToOne
    @MapsId("playlistId")
    private PlaylistsDAO playlist;

    @ManyToOne
    @MapsId("userId")
    private UsersDAO user;

    @ManyToOne
    @MapsId("roleId")
    private RolesDAO role;

    private Date invitationDate = new Date();

    public PlaylistUserRoleDAO() {
    }

    public PlaylistUserRoleDAO(PlaylistsDAO playlist, UsersDAO user, RolesDAO role, Date invitationDate) {
        this.playlist = playlist;
        this.user = user;
        this.role = role;
        this.id = new PlaylistUserRoleId(playlist.getId(), user.getId(), role.getId());
        this.invitationDate = invitationDate;
    }

    public PlaylistUserRoleId getId() {
        return id;
    }

    public void setId(PlaylistUserRoleId id) {
        this.id = id;
    }

    public PlaylistsDAO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistsDAO playlist) {
        this.playlist = playlist;
    }

    public UsersDAO getUser() {
        return user;
    }

    public void setUser(UsersDAO user) {
        this.user = user;
    }

    public RolesDAO getRole() {
        return role;
    }

    public void setRole(RolesDAO role) {
        this.role = role;
    }

    public Date getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(Date invitationDate) {
        this.invitationDate = invitationDate;
    }
}
