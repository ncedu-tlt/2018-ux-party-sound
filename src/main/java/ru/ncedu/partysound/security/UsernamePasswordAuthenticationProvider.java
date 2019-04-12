package ru.ncedu.partysound.security;


import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.repositories.UsersRepository;



public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private UsersRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(UsersRepository userRepository,
                                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials()
                .toString();
        UsersDAO usersDAO;
        if((usersDAO = userRepository.findByLogin(username)) != null && passwordEncoder.matches(password,userRepository.findByLogin(username).getPassword())) {
            Object principal = new UserPrincipal(usersDAO.getLogin());
            return new UsernamePasswordAuthenticationToken(principal, password, AuthorityUtils.createAuthorityList("ROLE_USER"));
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return  UsernamePasswordAuthenticationToken.class.equals(auth);
    }
}
