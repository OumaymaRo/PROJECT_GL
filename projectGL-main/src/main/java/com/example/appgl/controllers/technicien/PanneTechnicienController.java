package com.example.appgl.controllers.technicien;

import com.example.appgl.models.panne;
import com.example.appgl.models.technicien;
import com.example.appgl.services.panne.PanneService;
import com.example.appgl.services.technicien.TechnicienService;
import com.example.appgl.services.livraison.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/technicien/pannes")
public class PanneTechnicienController {

    private static final int MAX_RAPPORT_LENGTH = 3000; // Limite pour le rapport de réparation

    @Autowired
    private PanneService panneService;

    @Autowired
    private TechnicienService technicienService;

    @Autowired
    private RessourceService ressourceService;

    @GetMapping
    public String afficherPannes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        technicien technicien = technicienService.getTechnicienByUsername(username);
        
        if (technicien != null) {
            List<panne> pannes = panneService.getPannesByTechnicien(technicien.getId());
            model.addAttribute("pannes", pannes);
            return "technicien/pannes";
        }
        
        return "redirect:/login";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> getPanneDetails(@PathVariable Integer id) {
        panne panne = panneService.getPanneById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (panne != null) {
            // Informations de la ressource
            Map<String, Object> ressourceInfo = new HashMap<>();
            ressourceInfo.put("id", panne.getRessource().getId());
            ressourceInfo.put("nom", panne.getRessource().getNom());
            ressourceInfo.put("type", panne.getRessource().getType());
            ressourceInfo.put("marque", panne.getRessource().getMarque());
            
            // Informations du département
            if (panne.getRessource().getDepartement() != null) {
                Map<String, Object> departementInfo = new HashMap<>();
                departementInfo.put("id", panne.getRessource().getDepartement().getId());
                departementInfo.put("nom", panne.getRessource().getDepartement().getNom());
                ressourceInfo.put("departement", departementInfo);
            }
            
            // Informations de l'enseignant
            if (panne.getEnseignant() != null) {
                Map<String, Object> enseignantInfo = new HashMap<>();
                enseignantInfo.put("id", panne.getEnseignant().getId());
                enseignantInfo.put("username", panne.getEnseignant().getUsername());
                response.put("enseignant", enseignantInfo);
            }
            
            // Informations du technicien
            if (panne.getTechnicien() != null) {
                Map<String, Object> technicienInfo = new HashMap<>();
                technicienInfo.put("id", panne.getTechnicien().getId());
                technicienInfo.put("username", panne.getTechnicien().getUsername());
                response.put("technicien", technicienInfo);
            }
            
            response.put("id", panne.getId());
            response.put("ressource", ressourceInfo);
            response.put("dateReclamation", panne.getDateReclamation());
            response.put("type", panne.getType());
            response.put("rapport", panne.getRapport());
        }
        
        return response;
    }

    @PostMapping("/{id}/reparer")
    @ResponseBody
    public ResponseEntity<String> marquerReparee(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        try {
            panne panne = panneService.getPanneById(id);
            if (panne != null) {
                String rapport = request.get("rapport");
                if (rapport == null || rapport.trim().isEmpty()) {
                    return ResponseEntity.badRequest().body("{\"error\": \"Le rapport est requis\"}");
                }

                if (rapport.length() > MAX_RAPPORT_LENGTH) {
                    return ResponseEntity.badRequest().body("{\"error\": \"Le rapport est trop long. Maximum " + MAX_RAPPORT_LENGTH + " caractères autorisés.\"}");
                }

                // Générer le rapport de réparation
                StringBuilder rapportComplet = new StringBuilder();
                rapportComplet.append("Rapport de réparation\n");
                rapportComplet.append("===================\n\n");
                
                // Informations de la ressource
                rapportComplet.append("Informations de la ressource:\n");
                rapportComplet.append("- Numéro: ").append(panne.getRessource().getId()).append("\n");
                rapportComplet.append("- Nom: ").append(panne.getRessource().getNom()).append("\n");
                rapportComplet.append("- Type: ").append(panne.getRessource().getType() != null ? panne.getRessource().getType() : "Non spécifié").append("\n");
                rapportComplet.append("- Département: ").append(panne.getRessource().getDepartement() != null ? panne.getRessource().getDepartement().getNom() : "Non affecté").append("\n\n");
                
                // Informations de la panne
                rapportComplet.append("Informations de la panne:\n");
                rapportComplet.append("- Date de réclamation: ").append(panne.getDateReclamation() != null ? panne.getDateReclamation() : "Non spécifiée").append("\n");
                rapportComplet.append("- Signalé par: ").append(panne.getEnseignant() != null ? panne.getEnseignant().getUsername() : "Non spécifié").append("\n");
                rapportComplet.append("- Technicien: ").append(panne.getTechnicien() != null ? panne.getTechnicien().getUsername() : "Non affecté").append("\n");
                rapportComplet.append("- Date de réparation: ").append(LocalDateTime.now()).append("\n\n");
                
                // Rapport de réparation
                rapportComplet.append("Rapport de réparation:\n");
                rapportComplet.append(rapport).append("\n");
                
                // Vérifier la taille totale du rapport
                String rapportFinal = rapportComplet.toString();
                if (rapportFinal.length() > 4000) {
                    return ResponseEntity.badRequest().body("{\"error\": \"Le rapport complet est trop long. Veuillez réduire la taille du rapport de réparation.\"}");
                }
                
                // Mise à jour de la panne
                panne.setEstreparee(true);
                panne.setDateReparation(LocalDateTime.now());
                panne.setRapport(rapportFinal);
                panneService.savePanne(panne);

                // Mise à jour de l'état de la ressource
                panne.getRessource().setEtatpanne(false);
                ressourceService.saveRessource(panne.getRessource());
                
                return ResponseEntity.ok().body("{\"message\": \"Panne marquée comme réparée\"}");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/{id}/severe")
    @ResponseBody
    public ResponseEntity<String> marquerSevere(@PathVariable Integer id) {
        try {
            panne panne = panneService.getPanneById(id);
            if (panne != null) {
                panne.setType("severe");
                panneService.savePanne(panne);
                return ResponseEntity.ok().body("{\"message\": \"Panne marquée comme sévère\"}");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
} 