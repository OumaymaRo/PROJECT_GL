package com.example.appgl.services.livraison;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appgl.models.imprimante;
import com.example.appgl.models.ordinateur;
import com.example.appgl.models.ressource;
import com.example.appgl.repositories.livraison.RessourceRepository;

@Service
public class RessourceService {

    @Autowired
    private RessourceRepository ressourceRepository;

    public List<ressource> getAllRessources() {
        return ressourceRepository.findAll(); 
    }
    public ressource updateRessource(int id, ressource updatedRessource) {
    return ressourceRepository.findById(id).map(existing -> {
        existing.setNom(updatedRessource.getNom());
        existing.setEtataffectation(updatedRessource.isEtataffectation());
        existing.setEtatpanne(updatedRessource.isEtatpanne());
        existing.setMarque(updatedRessource.getMarque());

        if (existing instanceof ordinateur && updatedRessource instanceof ordinateur) {
            ordinateur o1 = (ordinateur) existing;
            ordinateur o2 = (ordinateur) updatedRessource;
            o1.setCpu(o2.getCpu());
            o1.setDisquedur(o2.getDisquedur());
            o1.setEcran(o2.getEcran());
            o1.setRam(o2.getRam());
        } else if (existing instanceof imprimante && updatedRessource instanceof imprimante) {
            imprimante i1 = (imprimante) existing;
            imprimante i2 = (imprimante) updatedRessource;
            i1.setResolution(i2.getResolution());
            i1.setVitesseimpression(i2.getVitesseimpression());
        }

        return ressourceRepository.save(existing);
    }).orElse(null);
}

}
