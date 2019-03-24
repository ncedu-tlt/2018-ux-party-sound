package ru.ncedu.partysound.services;

import ru.ncedu.partysound.models.domain.UsersDAO;

public interface UserService {
    UsersDAO save(UsersDAO user);
}