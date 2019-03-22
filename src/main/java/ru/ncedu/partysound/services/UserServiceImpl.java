package ru.ncedu.partysound.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UsersDAO user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UsersDAO findByUsername(String username) {
        return userRepository.findByLogin(username);
    }
}