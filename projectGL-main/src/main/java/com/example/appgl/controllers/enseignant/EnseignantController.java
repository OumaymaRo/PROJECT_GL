package com.example.appgl.controllers.enseignant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnseignantController {

    @GetMapping("/enseignant/dashboard")
    public String dashboard() {
        return "enseignant/dashboard";
    }
} 