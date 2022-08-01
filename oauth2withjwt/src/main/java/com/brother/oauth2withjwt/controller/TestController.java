package com.brother.oauth2withjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("test")
    public ResponseEntity<Map<String, String>> test() {
        return new ResponseEntity<>(Map.of("status", "success"), HttpStatus.OK);
    }

    @PostMapping("test")
    public ResponseEntity<Map<String, String>> test(@RequestBody String message) {
        return new ResponseEntity<>(Map.of("message", message), HttpStatus.OK);
    }
}
