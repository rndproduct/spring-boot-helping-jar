package com.brother.springboothttpsconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/greeting-message")
    public String greeting() {
        return "ACCESS BY HTTPS/SECURE PROTOCOL!";
    }
}
