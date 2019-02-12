package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<RolesDAO> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "recently_invited_users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "invited_user_id")}
    )
    private Set<UsersDAO> invitedUsers = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "recently_invited_users",
            joinColumns = {@JoinColumn(name = "invited_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<UsersDAO> invitedByUsers = new HashSet<>();

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

    public Set<RolesDAO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesDAO> roles) {
        this.roles = roles;
    }

    public Set<UsersDAO> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(Set<UsersDAO> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }

    public Set<UsersDAO> getInvitedByUsers() {
        return invitedByUsers;
    }

    public void setInvitedByUsers(Set<UsersDAO> invitedByUsers) {
        this.invitedByUsers = invitedByUsers;
    }
}
