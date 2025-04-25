package com.example.appgl.controllers.technicien;

import com.example.appgl.models.panne;
import com.example.appgl.models.technicien;
import com.example.appgl.services.panne.PanneService;
import com.example.appgl.services.technicien.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/technicien/pannes")
public class PanneTechnicienController {

    @Autowired
    private PanneService panneService;

    @Autowired
    private TechnicienService technicienService;

    @GetMapping
    public String afficherPannes(Model model) {
        // Récupérer le technicien connecté
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
    public panne getPanneDetails(@PathVariable Integer id) {
        return panneService.getPanneById(id);
    }

    @PostMapping("/{id}/reparer")
    @ResponseBody
    public ResponseEntity<String> marquerReparee(@PathVariable Integer id) {
        try {
            panne panne = panneService.getPanneById(id);
            if (panne != null) {
                panne.setEstreparee(true);
                panneService.savePanne(panne);
                return ResponseEntity.ok().body("{\"message\": \"Panne marquée comme réparée\"}");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
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