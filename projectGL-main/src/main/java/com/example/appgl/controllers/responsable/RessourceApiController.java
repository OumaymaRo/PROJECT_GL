package com.example.appgl.controllers.responsable;

import com.example.appgl.models.imprimante;
import com.example.appgl.models.ordinateur;
import com.example.appgl.models.ressource;
import com.example.appgl.models.departement;
import com.example.appgl.models.enseignant;
import com.example.appgl.repositories.livraison.RessourceRepository;
import com.example.appgl.repositories.livraison.DepartementRepository;
import com.example.appgl.repositories.livraison.EnseignantRepository;
import com.example.appgl.services.livraison.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/responsable/livraisons")
@CrossOrigin("*")
public class RessourceApiController {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private RessourceService ressourceService;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @GetMapping("/ressources")
    public String listRessources(Model model) {
        List<ressource> ressources = ressourceService.getAllRessources();
        List<departement> departements = departementRepository.findAll();
        model.addAttribute("ressources", ressources);
        model.addAttribute("departements", departements);
        return "responsable/liste-ressources";
    }
    @DeleteMapping("/ressources/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Integer id) {
        boolean isDeleted = ressourceService.deleteRessource(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Retourne un statut 204 (No Content)
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 si la ressource n'est pas trouvée
        }
    }
    @GetMapping("/ressources/{id}")
    @ResponseBody
    public Map<String, Object> getRessourceDetails(@PathVariable Integer id) {
        ressource ressource = ressourceRepository.findById(id).orElse(null);
        if (ressource != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", ressource.getId());
            response.put("nom", ressource.getNom());
            response.put("marque", ressource.getMarque());
            response.put("etataffectation", ressource.isEtataffectation());
            response.put("etatpanne", ressource.isEtatpanne());
            
            // Déterminer le type et ajouter les spécifications appropriées
            if (ressource instanceof ordinateur) {
                ordinateur ord = (ordinateur) ressource;
                response.put("type", "ordinateur");
                response.put("cpu", ord.getCpu());
                response.put("ram", ord.getRam());
                response.put("disquedur", ord.getDisquedur());
                response.put("ecran", ord.getEcran());
            } else if (ressource instanceof imprimante) {
                imprimante imp = (imprimante) ressource;
                response.put("type", "imprimante");
                response.put("resolution", imp.getResolution());
                response.put("vitesseimpression", imp.getVitesseimpression());
            }
            
            // Gérer le département de manière sécurisée
            if (ressource.getDepartement() != null) {
                Map<String, Object> departementMap = new HashMap<>();
                departementMap.put("id", ressource.getDepartement().getId());
                departementMap.put("nom", ressource.getDepartement().getNom());
                response.put("departement", departementMap);
            } else {
                response.put("departement", null);
            }
            
            // Gérer l'enseignant de manière sécurisée
            if (ressource.getEnseignant() != null) {
                Map<String, Object> enseignantMap = new HashMap<>();
                enseignantMap.put("id", ressource.getEnseignant().getId());
                enseignantMap.put("username", ressource.getEnseignant().getUsername());
                response.put("enseignant", enseignantMap);
            } else {
                response.put("enseignant", null);
            }

            return response;
        }
        return null;
    }
    
    @PostMapping("/ressources")
    @ResponseBody
    public ResponseEntity<?> addRessource(@RequestBody Map<String, Object> ressourceData) {
        try {
            System.out.println("Données reçues: " + ressourceData);
            ressource resourceToSave;
            String type = (String) ressourceData.get("type");
            System.out.println("Type de ressource: " + type);
            
            if ("ordinateur".equals(type)) {
                ordinateur ord = new ordinateur();
                ord.setId((Integer) ressourceData.get("id"));
                ord.setNom((String) ressourceData.get("nom"));
                ord.setMarque((String) ressourceData.get("marque"));
                ord.setEtataffectation((Boolean) ressourceData.get("etataffectation"));
                ord.setEtatpanne((Boolean) ressourceData.get("etatpanne"));
                ord.setCpu((String) ressourceData.get("cpu"));
                ord.setRam((String) ressourceData.get("ram"));
                ord.setDisquedur((String) ressourceData.get("disquedur"));
                ord.setEcran((String) ressourceData.get("ecran"));
                resourceToSave = ord;
                System.out.println("Ordinateur créé: " + ord);
            } else if ("imprimante".equals(type)) {
                imprimante imp = new imprimante();
                imp.setId((Integer) ressourceData.get("id"));
                imp.setNom((String) ressourceData.get("nom"));
                imp.setMarque((String) ressourceData.get("marque"));
                imp.setEtataffectation((Boolean) ressourceData.get("etataffectation"));
                imp.setEtatpanne((Boolean) ressourceData.get("etatpanne"));
                imp.setResolution((String) ressourceData.get("resolution"));
                imp.setVitesseimpression((String) ressourceData.get("vitesseimpression"));
                resourceToSave = imp;
                System.out.println("Imprimante créée: " + imp);
            } else {
                throw new IllegalArgumentException("Type de ressource inconnu: " + type);
            }

            // Gérer le département si présent
            if (ressourceData.containsKey("departement")) {
                Map<String, Object> departementData = (Map<String, Object>) ressourceData.get("departement");
                if (departementData != null && departementData.containsKey("id")) {
                    departement dep = new departement();
                    dep.setId((Integer) departementData.get("id"));
                    resourceToSave.setDepartement(dep);
                    System.out.println("Département associé: " + dep);
                }
            }

            // Gérer l'enseignant si présent
            if (ressourceData.containsKey("enseignant")) {
                Map<String, Object> enseignantData = (Map<String, Object>) ressourceData.get("enseignant");
                if (enseignantData != null && enseignantData.containsKey("id")) {
                    enseignant ens = new enseignant();
                    ens.setId(Long.valueOf((Integer) enseignantData.get("id")));
                    resourceToSave.setEnseignant(ens);
                    System.out.println("Enseignant associé: " + ens);
                }
            }

            System.out.println("Tentative de sauvegarde de la ressource: " + resourceToSave);
            ressource savedRessource = ressourceService.saveRessource(resourceToSave);
            System.out.println("Ressource sauvegardée avec succès: " + savedRessource);
            return ResponseEntity.ok(savedRessource);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la ressource: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ressources/enseignants/{departementId}")
    @ResponseBody
    public List<enseignant> getEnseignantsByDepartement(@PathVariable Integer departementId) {
        return enseignantRepository.findByDepartementId(departementId);
    }

}