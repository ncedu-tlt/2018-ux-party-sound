package ru.ncedu.partysound.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
