package ru.ncedu.partysound.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.repositories.UsersRepository;

@Component
public class UserValidator implements Validator {

    private final UsersRepository usersRepository;

    public UserValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UsersDAO user = (UsersDAO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
        if (user.getLogin().length() < 2 || user.getLogin().length() > 32) {
            errors.rejectValue("login", "Size.userForm.username");
        }
        if (usersRepository.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "NotEmpty");
        if (usersRepository.findByMail(user.getMail()) != null) {
            errors.rejectValue("mail", "Duplicate.userForm.mail");
        }
        if (!user.getMail().matches("\\w+@\\w+.\\w+")) {
            errors.rejectValue("mail", "Invalid.userForm.mail");
        }

    }
}