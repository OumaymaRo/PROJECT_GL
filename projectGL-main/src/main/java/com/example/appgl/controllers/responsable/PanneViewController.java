package com.example.appgl.controllers.responsable;

import com.example.appgl.models.ressource;
import com.example.appgl.models.technicien;
import com.example.appgl.models.panne;
import com.example.appgl.services.livraison.RessourceService;
import com.example.appgl.services.technicien.TechnicienService;
import com.example.appgl.services.panne.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/responsable/livraisons/ressources")
public class PanneViewController {

    @Autowired
    private RessourceService ressourceService;

    @Autowired
    private TechnicienService technicienService;

    @Autowired
    private PanneService panneService;

    @GetMapping("/pannes")
    public String afficherPagePannes(Model model) {
        List<ressource> ressources = ressourceService.getAllRessources();
        List<technicien> techniciens = technicienService.getAllTechniciens();
        model.addAttribute("ressources", ressources);
        model.addAttribute("techniciens", techniciens);
        return "responsable/liste-pannes";
    }

    @PostMapping("/{id}/affecter-technicien")
    @ResponseBody
    public ResponseEntity<String> affecterTechnicien(@PathVariable Long id, @RequestBody AffectationRequest request) {
        try {
            ressource ressource = ressourceService.getAllRessources().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
                
            if (ressource != null) {
                technicien technicien = technicienService.getTechnicienById(request.getTechnicienId());
                if (technicien != null) {
                    // Créer une nouvelle panne avec le technicien affecté
                    panne panne = new panne();
                    panne.setRessource(ressource);
                    panne.setTechnicien(technicien);
                    panne.setEstreparee(false);
                    panneService.savePanne(panne);
                    
                    // Mettre à jour l'état de la ressource
                    ressource.setEtatpanne(false);
                    ressourceService.saveRessource(ressource);
                    
                    return ResponseEntity.ok().body("{\"message\": \"Technicien affecté avec succès\"}");
                } else {
                    return ResponseEntity.badRequest().body("{\"error\": \"Technicien non trouvé\"}");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // Classe interne pour la requête d'affectation
    private static class AffectationRequest {
        private Long technicienId;

        public Long getTechnicienId() {
            return technicienId;
        }

        public void setTechnicienId(Long technicienId) {
            this.technicienId = technicienId;
        }
    }
}
