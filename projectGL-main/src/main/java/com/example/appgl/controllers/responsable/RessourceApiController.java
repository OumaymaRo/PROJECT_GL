package com.example.appgl.controllers.responsable;

import com.example.appgl.models.imprimante;
import com.example.appgl.models.ordinateur;
import com.example.appgl.models.ressource;
import com.example.appgl.repositories.livraison.RessourceRepository;
import com.example.appgl.services.livraison.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/responsable/livraisons")
@CrossOrigin("*")
public class RessourceApiController {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private RessourceService ressourceService;

    @GetMapping("/ressources")
    public String listRessources(Model model) {
        List<ressource> ressources = ressourceService.getAllRessources();
        model.addAttribute("ressources", ressources);
        return "responsable/liste-ressources";
    }

    @GetMapping("/ressources/{id}")
    @ResponseBody
    public ressource getRessourceDetails(@PathVariable Integer id) {
        return ressourceRepository.findById(id).orElse(null);
    }

    @PostMapping("/ressources")
    @ResponseBody
    public ressource addRessource(@RequestBody ressource ressource) {
        return ressourceService.saveRessource(ressource);
    }
} 