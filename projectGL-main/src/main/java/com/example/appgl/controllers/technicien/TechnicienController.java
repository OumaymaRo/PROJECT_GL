package com.example.appgl.controllers.technicien;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TechnicienController {

    @GetMapping("/technicien/dashboard")
    public String dashboard() {
        return "technicien/dashboard";
    }
} 