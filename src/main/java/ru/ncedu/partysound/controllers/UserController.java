package ru.ncedu.partysound.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.services.UserService;
import ru.ncedu.partysound.validation.UserValidator;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UsersDAO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        userService.save(userForm);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public String getAuthUser(Principal principal) {

        return principal.getName();
    }

}