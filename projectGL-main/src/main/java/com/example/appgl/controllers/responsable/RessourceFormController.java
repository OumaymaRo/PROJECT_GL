// package com.example.appgl.controllers.responsable;

// import com.example.appgl.models.imprimante;
// import com.example.appgl.models.ordinateur;
// import com.example.appgl.models.ressource;
// import com.example.appgl.services.livraison.RessourceService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;

// @Controller
// public class RessourceFormController {

//     @Autowired
//     private RessourceService ressourceService;

//     // Cette méthode gère l'ajout d'une nouvelle ressource
//     @PostMapping("/responsable/livraisons/ajouterRessource")
//     public String ajouterRessource(@ModelAttribute("ressource") ressource r, Model model) {
//         if ("ordinateur".equalsIgnoreCase(r.getType())) {
//             ordinateur o = (ordinateur) r;  // On cast la ressource en ordinateur
//             ressourceService.saveOrdinateur(o);
//         } else if ("imprimante".equalsIgnoreCase(r.getType())) {
//             imprimante i = (imprimante) r;  // On cast la ressource en imprimante
//             ressourceService.saveImprimante(i);
//         }
//         return "redirect:/";  // Redirection vers la page d'accueil ou une autre page
//     }
// }
