package com.example.appgl.controllers.responsable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appgl.models.ressource;
import com.example.appgl.services.livraison.RessourceService;

@RestController
@RequestMapping("/responsable/livraisons")
@CrossOrigin("*")
public class RessourceController {

    @Autowired
    private RessourceService ressourceService;

    @GetMapping
    public ResponseEntity<List<ressource>> getAllRessources() {
        return ResponseEntity.ok(ressourceService.getAllRessources());
    }

    
}