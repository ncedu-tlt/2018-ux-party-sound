package ru.ncedu.partysound.models.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlaylistUserRoleId implements Serializable {

    @Column
    private long playlistId;

    @Column
    private long userId;

    @Column
    private long roleId;

    public PlaylistUserRoleId() {
    }

    public PlaylistUserRoleId(long playlistId, long userId, long roleId) {
        this.playlistId = playlistId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistUserRoleId that = (PlaylistUserRoleId) o;
        return playlistId == that.playlistId &&
                userId == that.userId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, userId, roleId);
    }
}
