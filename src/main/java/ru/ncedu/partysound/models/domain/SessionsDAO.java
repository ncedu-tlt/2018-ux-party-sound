package ru.ncedu.partysound.models.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sessions")
public class SessionsDAO implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersDAO user;

    public SessionsDAO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UsersDAO getUser() {
        return user;
    }

    public void setUser(UsersDAO user) {
        this.user = user;
    }
}
