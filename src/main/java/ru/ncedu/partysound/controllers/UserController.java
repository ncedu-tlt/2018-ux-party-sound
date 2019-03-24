package ru.ncedu.partysound.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.services.UserService;
import ru.ncedu.partysound.validation.UserValidator;

@RestController
@RequestMapping("auth")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UsersDAO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return String.valueOf(bindingResult);
        }
        userService.save(userForm);
        return "successful registration";
    }

    @GetMapping("/login")
    public String login() {
        return "successful login";
    }

}