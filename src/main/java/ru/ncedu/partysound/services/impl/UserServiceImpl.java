package ru.ncedu.partysound.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.repositories.UsersRepository;
import ru.ncedu.partysound.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository userRepository;
    private final PasswordEncoder encode;

    public UserServiceImpl(UsersRepository userRepository, PasswordEncoder encode) {
        this.userRepository = userRepository;
        this.encode = encode;
    }

    @Override
    public UsersDAO save(UsersDAO user) {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.setLogin(user.getLogin());
        usersDAO.setPassword(encode.encode(user.getPassword()));
        usersDAO.setMail(user.getMail());
        usersDAO.setName(user.getName());
        usersDAO.setSurname(user.getSurname());
        return userRepository.save(usersDAO);
    }


}