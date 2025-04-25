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

    public boolean deleteRessource(Integer id) {
        if (ressourceRepository.existsById(id)) {
            ressourceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ressource saveRessource(ressource r) {
        try {
            System.out.println("Tentative de sauvegarde dans le service: " + r);
            if (r instanceof ordinateur) {
                ordinateur ord = (ordinateur) r;
                System.out.println("Sauvegarde d'un ordinateur: " + ord);
                return ordinateurRepository.save(ord);
            } else if (r instanceof imprimante) {
                imprimante imp = (imprimante) r;
                System.out.println("Sauvegarde d'une imprimante: " + imp);
                return imprimanteRepository.save(imp);
            } else {
                System.out.println("Sauvegarde d'une ressource de base: " + r);
                return ressourceRepository.save(r);
            }
        } catch (Exception e) {
            System.err.println("Erreur dans le service lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}