package ru.ncedu.partysound.models.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "users")
public class UsersDAO {

    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String name;
    private String surname;
    private String mail;
    private String avatar;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<SessionsDAO> sessions = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<PlaylistUserRoleDAO> playlistRole = new HashSet<>();

    public Set<SessionsDAO> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SessionsDAO> sessions) {
        this.sessions = sessions;
    }

    public UsersDAO() {
    }

    public UsersDAO(String login, String name, String surname, String mail, String avatar, String password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.avatar = avatar;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PlaylistUserRoleDAO> getPlaylistRole() {
        return playlistRole;
    }

    public void setPlaylistRole(Set<PlaylistUserRoleDAO> playlistRole) {
        this.playlistRole = playlistRole;
    }
}
