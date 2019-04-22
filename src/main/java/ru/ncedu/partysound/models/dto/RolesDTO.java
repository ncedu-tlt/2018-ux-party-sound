package ru.ncedu.partysound.models.dto;

public class RolesDTO {
    private long id;

    private String name;
    private boolean addTrack;
    private boolean deleteTrack;
    private boolean deleteUser;
    private boolean changeTracksOrder;
    private boolean seePlaylist;

    public RolesDTO() {
    }

    public RolesDTO(long id, String name, boolean addTrack, boolean deleteTrack, boolean deleteUser, boolean changeTracksOrder, boolean seePlaylist) {
        this.id = id;
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
