package com.hgebk.dokobackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HealthController {
    @GetMapping
    public String reportHealth() {
        return "Eat a healthy breakfast!";
    }
}
