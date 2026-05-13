package com.example.otel_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @GetMapping("/")
    public String index() {
        log.info("Handling root request");
        return "Hello World";
    }

    @PostMapping("/users")
    public String createUser(@RequestBody Map<String, String> user) {
        log.info("Creating user: {}", user.get("name"));
        return "User " + user.get("name") + " created";
    }
}
