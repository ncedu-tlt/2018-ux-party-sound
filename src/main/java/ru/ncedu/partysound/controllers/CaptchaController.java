package ru.ncedu.partysound.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.partysound.configs.JamendoAPI;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    @GetMapping("/key")
    public String get() {
        return System.getenv(JamendoAPI.JAMENDO_CLIENT_ID);
    }

}
