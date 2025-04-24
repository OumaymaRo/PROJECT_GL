package com.example.appgl.controllers.responsable;

import com.example.appgl.models.imprimante;
import com.example.appgl.models.ordinateur;
import com.example.appgl.models.ressource;
import com.example.appgl.repositories.livraison.RessourceRepository;
import com.example.appgl.services.livraison.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsable/livraisons")
@CrossOrigin("*")
public class RessourceApiController {

    @Autowired

    private RessourceRepository ressourceRepository;    

    @GetMapping
    public List<ressource> getAllRessources() {
       return ressourceRepository.findAll();
    }
    @Autowired
    private RessourceService ressourceService;

    // Pour ajouter une ressource selon le type (ordinateur ou imprimante)
    @PostMapping("/ajouterRessource")
    public ResponseEntity<ressource> createRessource(@RequestBody ressource r) {
        try {
            if (r.getType().equals("ordinateur")) {
                // Si la ressource est un ordinateur
                ordinateur ordi = (ordinateur) r; // Casting en ordinateur
                ressource savedRessource = ressourceService.saveRessource(ordi);
                return ResponseEntity.ok(savedRessource); // Retourner la ressource sauvegardée
            } else if (r.getType().equals("imprimante")) {
                // Si la ressource est une imprimante
                imprimante impr = (imprimante) r; // Casting en imprimante
                ressource savedRessource = ressourceService.saveRessource(impr);
                return ResponseEntity.ok(savedRessource); // Retourner la ressource sauvegardée
            } else {
                return ResponseEntity.badRequest().body(null); // Si type inconnu
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // En cas d'erreur
        }
    }
}
