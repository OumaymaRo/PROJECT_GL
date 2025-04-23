package com.example.appgl.controllers.chefdepartement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChefDepartementController {

    @GetMapping("/chef-departement/dashboard")
    public String dashboard() {
        return "chef-departement/dashboard";
    }
} 