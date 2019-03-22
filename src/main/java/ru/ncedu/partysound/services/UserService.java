package ru.ncedu.partysound.services;


import ru.ncedu.partysound.models.domain.UsersDAO;

public interface UserService {
    void save(UsersDAO user);

    UsersDAO findByUsername(String username);
}