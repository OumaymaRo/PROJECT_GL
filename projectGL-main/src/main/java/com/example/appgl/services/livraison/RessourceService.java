package com.example.appgl.services.livraison;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appgl.models.imprimante;
import com.example.appgl.models.ordinateur;
import com.example.appgl.models.ressource;
import com.example.appgl.repositories.livraison.ImprimanteRepository;
import com.example.appgl.repositories.livraison.OrdinateurRepository;
import com.example.appgl.repositories.livraison.RessourceRepository;

@Service
public class RessourceService {


    @Autowired
    private OrdinateurRepository ordinateurRepository;

    @Autowired
    private ImprimanteRepository imprimanteRepository;
    @Autowired
    private RessourceRepository ressourceRepository;

    public List<ressource> getAllRessources() {
        return ressourceRepository.findAll(); 
    }
    // public ressource saveRessource(ressource r) {
    //     return ressourceRepository.save(r);
    // }
 // Sauvegarder une ressource (ordinateur ou imprimante)
 public ressource saveRessource(ressource r) {
    if (r instanceof ordinateur) {
        // Si la ressource est un ordinateur, on l'enregistre dans OrdinateurRepository
        return ordinateurRepository.save((ordinateur) r);
    } else if (r instanceof imprimante) {
        // Si la ressource est une imprimante, on l'enregistre dans ImprimanteRepository
        return imprimanteRepository.save((imprimante) r);
    } else {
        // Si la ressource est d'un type inconnu
        throw new IllegalArgumentException("Type de ressource inconnu");
    }
    }
}

