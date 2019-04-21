package ru.ncedu.partysound.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.ncedu.partysound.repositories.UsersRepository;
import ru.ncedu.partysound.security.NonRedirectingAuthenticationSuccessHandler;
import ru.ncedu.partysound.security.UsernamePasswordAuthenticationProvider;


import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    private final UsersRepository usersRepository;
    @Autowired
    private NonRedirectingAuthenticationSuccessHandler NonRedirectingAuthenticationSuccessHandler;

    public SecurityConfig(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        return new UsernamePasswordAuthenticationProvider(usersRepository, passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/api/auth/login").permitAll()
                    .antMatchers("/api/protected/* ").authenticated()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/api/auth/login")
                    .successHandler(NonRedirectingAuthenticationSuccessHandler)
                    .failureHandler((httpServletRequest, httpServletResponse, e) -> {httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);})
                .and()
                    .logout()
                    .logoutSuccessHandler(((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)))
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(
                        (request, response, e) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                )
                .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

}
