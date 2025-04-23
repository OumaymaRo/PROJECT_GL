package com.example.appgl.controllers.fournisseur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FournisseurController {

    private static final Logger logger = LoggerFactory.getLogger(FournisseurController.class);

    @GetMapping("/fournisseur/dashboard")
    public String dashboard() {
        logger.info("Accès au dashboard du fournisseur");
        return "fournisseur/dashboard";
    }
} 