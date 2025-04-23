package com.example.appgl.controllers;

import com.example.appgl.models.Role;
import com.example.appgl.models.User;
import com.example.appgl.repositories.RoleRepository;
import com.example.appgl.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();
                if (role.equals("ROLE_FOURNISSEUR")) {
                    return "redirect:/fournisseur/dashboard";
                } else if (role.equals("ROLE_ENSEIGNANT")) {
                    return "redirect:/enseignant/dashboard";
                } else if (role.equals("ROLE_TECHNICIEN")) {
                    return "redirect:/technicien/dashboard";
                } else if (role.equals("ROLE_RESPONSABLE")) {
                    return "redirect:/responsable/dashboard";
                } else if (role.equals("ROLE_CHEF_DEPARTEMENT")) {
                    return "redirect:/chef-departement/dashboard";
                }
            }
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();
                if (role.equals("ROLE_FOURNISSEUR")) {
                    return "redirect:/fournisseur/dashboard";
                } else if (role.equals("ROLE_ENSEIGNANT")) {
                    return "redirect:/enseignant/dashboard";
                } else if (role.equals("ROLE_TECHNICIEN")) {
                    return "redirect:/technicien/dashboard";
                } else if (role.equals("ROLE_RESPONSABLE")) {
                    return "redirect:/responsable/dashboard";
                } else if (role.equals("ROLE_CHEF_DEPARTEMENT")) {
                    return "redirect:/chef-departement/dashboard";
                }
            }
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String role) {
        try {
            logger.info("Tentative d'inscription pour l'utilisateur: " + username);
            logger.info("Rôle sélectionné: " + role);

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setEmail(email);

            String roleName = "ROLE_" + role.toUpperCase();
            logger.info("Recherche du rôle: " + roleName);

            Role userRole = roleRepository.findByNom(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            newUser.setRoles(roles);

            userRepository.save(newUser);
            logger.info("Utilisateur enregistré avec succès: " + username);
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("Erreur lors de l'inscription: " + e.getMessage());
            return "redirect:/register?error=true";
        }
    }
}