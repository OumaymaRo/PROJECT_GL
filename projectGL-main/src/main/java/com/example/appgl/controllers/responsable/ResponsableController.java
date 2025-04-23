package com.example.appgl.controllers.responsable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResponsableController {

    @GetMapping("/responsable/dashboard")
    public String dashboard() {
        return "responsable/dashboard";
    }
} 